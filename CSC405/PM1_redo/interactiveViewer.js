var interactiveViewer = function(){
    var gl;
    var canvas;

    var position = [];
    var colors = [];
    var normals = [];
    var numPositions = 36;
    var vBuffer;
    var cBuffer;
    var nBuffer;

    var near = 0.1;
    var far = 5.0;
    var radius = 1.0;
    var theta = 0.0;
    var phi = 0.0;

    var left = -1.5;
    var right = 1.5;
    var top = 1.5;
    var bottom = -1.5;
    
    var fovy = 100;
    var aspect;
    var perspect = 0;
    var whichShader = 0;
    var regProgram;
    var fancyProgram;

    var fancyPositionLoc;
    var fancyNormalLoc;
    var fancyModelViewMatrixLoc;
    var fancyProjectionMatrixLoc;
    var fancyNormalMatrixLoc;

    var ambientProductLoc;
    var diffuseProductLoc;
    var specularProductLoc;
    var lightPositionLoc;
    var shininessLoc;

    var lightPosition = vec4(1.0, 1.0, 1.0, 0.0);
    var lightAmbient = vec4(0.2, 0.2, 0.2, 1.0);
    var lightDiffuse = vec4(1.0, 1.0, 1.0, 1.0);
    var lightSpecular = vec4(1.0, 1.0, 1.0, 1.0);

    var modelViewMatrix;
    var projectionMatrix;
    var modelViewMatrixLoc;
    var projectionMatrixLoc;

    var fancyModelViewMatrixLoc;
    var fancyProjectionMatrixLoc;

    const at = vec3(0.0, 0.0, 0.0);
    const up = vec3(0.0, 1.0, 0.0);

    let s = 0.5;

    var vertices = [
        vec4(-s, -s,  s, 1.0),
        vec4(-s,  s,  s, 1.0),
        vec4(s,  s,  s, 1.0),
        vec4(s, -s,  s, 1.0),
        vec4(-s, -s, -s, 1.0),
        vec4(-s,  s, -s, 1.0),
        vec4(s,  s, -s, 1.0),
        vec4(s, -s, -s, 1.0)
    ];

    var vertexColors = [
        [0.0, 0.0, 0.0, 1.0],  // black
        [0.0, 0.0, 0.8, 0.3],  // purple
        [0.7, 1.0, 0.0, 1.0],  // yellow/green
        [0.0, 1.0, 0.0, 1.0],  // green
        [0.0, 0.0, 0.8, 1.0],  // blue/green
        [1.0, 0.0, 1.0, 1.0],  // magenta
        [0.0, 0.8, 0.7, 1.0],  // cyan
        [1.0, 1.0, 1.0, 1.0]   // white
    ];

function startShaders(gl, vsc, fsc){

    var vertShader = gl.createShader(gl.VERTEX_SHADER);
    gl.shaderSource(vertShader, vsc);
    gl.compileShader(vertShader);
    if (!gl.getShaderParameter(vertShader, gl.COMPILE_STATUS)) {
        console.error("Vertex shader error:", gl.getShaderInfoLog(vertShader));
    }

    var fragShader = gl.createShader(gl.FRAGMENT_SHADER);
    gl.shaderSource(fragShader, fsc);
    gl.compileShader(fragShader);
    if (!gl.getShaderParameter(fragShader, gl.COMPILE_STATUS)) {
        console.error("Fragment shader error:", gl.getShaderInfoLog(fragShader));
    }

    var shaderProgram = gl.createProgram();
    gl.attachShader(shaderProgram, vertShader);
    gl.attachShader(shaderProgram, fragShader);
    gl.linkProgram(shaderProgram);
    if (!gl.getProgramParameter(shaderProgram, gl.LINK_STATUS)) {
        console.error("Shader link error:", gl.getProgramInfoLog(shaderProgram));
    }
    
    return shaderProgram;
}

function setupRegularShader(){

    regProgram = startShaders(gl, vertexShaderRegularCode, fragmentShaderCode); //standard shader

    var colorLoc = gl.getAttribLocation(regProgram, "aColor");
    gl.vertexAttribPointer(colorLoc, 4, gl.FLOAT, false, 0, 0);
    gl.enableVertexAttribArray(colorLoc);

    var positionLoc = gl.getAttribLocation(regProgram, "aPosition");
    gl.vertexAttribPointer(positionLoc, 4, gl.FLOAT, false, 0, 0);
    gl.enableVertexAttribArray(positionLoc);

    modelViewMatrixLoc = gl.getUniformLocation(regProgram, "uModelViewMatrix");
    projectionMatrixLoc = gl.getUniformLocation(regProgram, "uProjectionMatrix");

    return regProgram;
}

function setupFancyShader(){
    
    fancyProgram = startShaders(gl, vertexShaderFancyCode, fragmentShaderCode); //shader with light source

    fancyPositionLoc = gl.getAttribLocation(fancyProgram, "aPosition");
    fancyNormalLoc = gl.getAttribLocation(fancyProgram, "aNormal");

    fancyModelViewMatrixLoc = gl.getUniformLocation(fancyProgram, "modelViewMatrix");
    fancyProjectionMatrixLoc = gl.getUniformLocation(fancyProgram, "projectionMatrix");
    fancyNormalMatrixLoc = gl.getUniformLocation(fancyProgram, "normalMatrix");

    ambientProductLoc = gl.getUniformLocation(fancyProgram, "ambientProduct");
    diffuseProductLoc = gl.getUniformLocation(fancyProgram, "diffuseProduct");
    specularProductLoc = gl.getUniformLocation(fancyProgram, "specularProduct");
    lightPositionLoc = gl.getUniformLocation(fancyProgram, "lightPosition");
    shininessLoc = gl.getUniformLocation(fancyProgram, "shininess");
    
    return fancyProgram;
}

    //define a face: a, b, c, d where the face shape is:
    //  a  b
    //  d  c
    //we are winding around in a, b, c, etc order

    function face(a, b, c, d){

        var indices = [a, b, c, a, c, d]

        var t1 = subtract(vertices[b], vertices[a]);
        var t2 = subtract(vertices[c], vertices[b]);
        var normal = normalize(cross(t1, t2));

        for (i = 0; i < indices.length; i++){
            position.push(vertices[indices[i]]);
            colors.push(vertexColors[a]);
            normals.push(normal);
        }
    }

    function colorCube(){
        face(1, 0, 3, 2);
        face(2, 3, 7, 6);
        face(3, 0, 4, 7);
        face(6, 5, 1, 2);
        face(4, 5, 6, 7);
        face(5, 4, 0, 1);
    }

    function renderCube(a, b){

        gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

        x = radius * Math.sin(a) * Math.cos(b);
        y = radius * Math.cos(a);
        z = radius * Math.sin(a) * Math.sin(b);

        var eyeType = vec3(x, y, z);

        modelViewMatrix = lookAt(eyeType, at , up);

        if(perspect == 0){
            projectionMatrix = ortho(left, right, bottom, top, near, far);
        } else {
            projectionMatrix = perspective(fovy, aspect, near, far);
        }

        if(whichShader == 0){
            gl.useProgram(regProgram);

            gl.bindBuffer(gl.ARRAY_BUFFER, vBuffer);
            var positionLoc = gl.getAttribLocation(regProgram, "aPosition");
            gl.vertexAttribPointer(positionLoc, 4, gl.FLOAT, false, 0, 0);
            gl.enableVertexAttribArray(positionLoc);

            gl.bindBuffer(gl.ARRAY_BUFFER, cBuffer);
            var colorLoc = gl.getAttribLocation(regProgram, "aColor");
            gl.vertexAttribPointer(colorLoc, 4, gl.FLOAT, false, 0, 0);
            gl.enableVertexAttribArray(colorLoc);

            modelViewMatrixLoc = gl.getUniformLocation(regProgram, "uModelViewMatrix");
            projectionMatrixLoc = gl.getUniformLocation(regProgram, "uProjectionMatrix");
            gl.uniformMatrix4fv(modelViewMatrixLoc, false, flatten(modelViewMatrix));
            gl.uniformMatrix4fv(projectionMatrixLoc, false, flatten(projectionMatrix));

        } else {
            gl.useProgram(fancyProgram);

            gl.bindBuffer(gl.ARRAY_BUFFER, vBuffer);
            gl.vertexAttribPointer(fancyPositionLoc, 4, gl.FLOAT, false, 0, 0);
            gl.enableVertexAttribArray(fancyPositionLoc);

            gl.bindBuffer(gl.ARRAY_BUFFER, nBuffer);
            gl.vertexAttribPointer(fancyNormalLoc, 3, gl.FLOAT, false, 0, 0);
            gl.enableVertexAttribArray(fancyNormalLoc);

            gl.uniformMatrix4fv(fancyModelViewMatrixLoc, false, flatten(modelViewMatrix));
            gl.uniformMatrix4fv(fancyProjectionMatrixLoc, false, flatten(projectionMatrix));

            var nMatrix = normalMatrix(modelViewMatrix);
            gl.uniformMatrix3fv(fancyNormalMatrixLoc, false, flatten(nMatrix));

            gl.uniform4fv(ambientProductLoc, flatten(lightAmbient));
            gl.uniform4fv(diffuseProductLoc, flatten(lightDiffuse));
            gl.uniform4fv(specularProductLoc, flatten(lightSpecular));
            gl.uniform4fv(lightPositionLoc, flatten(lightPosition));
            gl.uniform1f(shininessLoc, 20.0);
        }

        
        gl.drawArrays(gl.TRIANGLES, 0, numPositions);
    }

    window.onload = function init(){

        canvas = document.getElementById('mycanvas');
        gl = canvas.getContext('webgl2');

        gl.viewport(0, 0, canvas.width, canvas.height);
        gl.clearColor(0.5, 0.5, 0.5, 1.0);
        gl.enable(gl.DEPTH_TEST);

        regProgram = setupRegularShader();
        fancyProgram = setupFancyShader();

        if(whichShader == 0){gl.useProgram(regProgram);} 
        else {gl.useProgram(fancyProgram);}

        colorCube();
        aspect  = 1.0;

        vBuffer = gl.createBuffer();
        gl.bindBuffer(gl.ARRAY_BUFFER, vBuffer);
        gl.bufferData(gl.ARRAY_BUFFER, flatten(position), gl.STATIC_DRAW);

        cBuffer = gl.createBuffer();
        gl.bindBuffer(gl.ARRAY_BUFFER, cBuffer);
        gl.bufferData(gl.ARRAY_BUFFER, flatten(colors), gl.STATIC_DRAW);

        nBuffer = gl.createBuffer();
        gl.bindBuffer(gl.ARRAY_BUFFER, nBuffer);
        gl.bufferData(gl.ARRAY_BUFFER, flatten(normals), gl.STATIC_DRAW);

        //
        //Buttons
        //

        document.getElementById("Button1").onclick = function(){ //Ortho perspective
            phi = Math.PI/4;
            theta = Math.PI/4;
            perspect = 0;
            renderCube(phi, theta);
        };

        document.getElementById("Button2").onclick = function(){ //Perspective perspective
            phi = Math.PI/4;
            theta = Math.PI/4;
            perspect = 1;
            renderCube(phi, theta);
        };

        document.getElementById("Button3").onclick = function(){ //Increase phi
            phi = phi + 0.2;
            renderCube(phi, theta);
        };

        document.getElementById("Button4").onclick = function(){ //Increase theta
            theta = theta + 0.2;
            renderCube(phi, theta);
        };

        document.getElementById("Button5").onclick = function(){ //Increase distance
            radius = radius + 0.2;
            renderCube(phi, theta);
        };

        document.getElementById("Button6").onclick = function(){ //Decrease distance
            radius = radius - 0.2;
            renderCube(phi, theta);
        };

        document.getElementById("Button7").onclick = function(){ //Randomize the colors
            
            for(i = 0; i < vertexColors.length; i++){
                var tempVec = vec4(vertexColors[i]);

                for(j = 0; j < 2; j++){
                    tempVec[j] = Math.random();
                }
                
                for(k = 0; k < tempVec.length; k++){
                    vertexColors[i][k] = tempVec[k];
                    console.log(vertexColors[i][k]);
                }
            }

            gl.clearColor(Math.random(), Math.random(), Math.random(), 1.0);
            colorCube();
            gl.bindBuffer(gl.ARRAY_BUFFER, cBuffer);
            gl.bufferData(gl.ARRAY_BUFFER, flatten(colors), gl.STATIC_DRAW);
            renderCube(phi, theta);
        };

        document.getElementById("Button8").onclick = function(){ //Change shader
            if(whichShader == 0){
                whichShader = 1;
            } else if (whichShader == 1){
                whichShader = 0;
            }
            renderCube(phi, theta);
        };
    }
}

interactiveViewer();