var interactiveViewer = function(){
    var gl;
    var canvas;

    var position = [];
    var colors = [];
    var numPositions = 36;

    var near = 0.1;
    var far = 3.0;
    var radius = 1.0;
    var theta = 0.0;
    var phi = 0.0;
    
    var fovy = 100;
    var aspect;

    var left = -1.5;
    var right = 1.5;
    var top = 1.5;
    var bottom = -1.5;
    var perspect = 0;

    var modelViewMatrix;
    var projectionMatrix;
    var modelViewMatrixLoc;
    var projectionMatrixLoc;

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
        [0.0, 0.0, 0.8, 0.3],  // purple; i'm sick of the bright colors
        [0.7, 1.0, 0.0, 1.0],  // yellow/green
        [0.0, 1.0, 0.0, 1.0],  // green
        [0.0, 0.0, 0.8, 1.0],  // blue/green
        [1.0, 0.0, 1.0, 1.0],  // magenta
        [0.0, 0.8, 0.7, 1.0],  // cyan
        [1.0, 1.0, 1.0, 1.0]   // white
    ];

    const vertexShaderCode = `#version 300 es

    in  vec4 aPosition;
    in  vec4 aColor;
    out vec4 vColor;

    uniform mat4 uModelViewMatrix;
    uniform mat4 uProjectionMatrix;

    void main()
    {
        gl_Position = uProjectionMatrix * uModelViewMatrix * aPosition;
        vColor = aColor;
    }`;

    const fragmentShaderCode = `#version 300 es

    precision mediump float;

    in vec4 vColor;
    out vec4 fColor;

    void main()
    {
        fColor = vColor;
    }`;

    //define a face: a, b, c, d where the face shape is:
    //  a  b
    //  d  c
    //we are winding around in a, b, c, etc order

    function face(a, b, c, d){
        var indices = [a, b, c, a, c, d]
        for (i = 0; i < indices.length; i++){
            position.push(vertices[indices[i]]);
            colors.push(vertexColors[a]);
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

    function startShaders(gl, vsc, fsc){

        var vertShader = gl.createShader(gl.VERTEX_SHADER);
        gl.shaderSource(vertShader, vsc);
        gl.compileShader(vertShader);

        var fragShader = gl.createShader(gl.FRAGMENT_SHADER);
        gl.shaderSource(fragShader, fsc);
        gl.compileShader(fragShader);

        var shaderProgram = gl.createProgram();
        gl.attachShader(shaderProgram, vertShader);
        gl.attachShader(shaderProgram, fragShader);
        gl.linkProgram(shaderProgram);
        
        return shaderProgram;
    }

    function renderCube(a, b, c, d){

        gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

        var eyeType = vec3(a, b, c);

        modelViewMatrix = lookAt(eyeType, at , up);

        if(d == 0){
            projectionMatrix = ortho(left, right, bottom, top, near, far);
        } else {
            projectionMatrix = perspective(fovy, aspect, near, far);
        }
        
        gl.uniformMatrix4fv(modelViewMatrixLoc, false, flatten(modelViewMatrix));
        gl.uniformMatrix4fv(projectionMatrixLoc, false, flatten(projectionMatrix));

        gl.drawArrays(gl.TRIANGLES, 0, numPositions);
    }

    window.onload = function init(){

        canvas = document.getElementById('mycanvas');
        gl = canvas.getContext('webgl2');

        gl.viewport(0, 0, canvas.width, canvas.height);
        gl.clearColor(1.0, 1.0, 1.0, 1.0);
        gl.enable(gl.DEPTH_TEST);

        var program = startShaders(gl, vertexShaderCode, fragmentShaderCode);
        gl.useProgram(program);

        colorCube();
        aspect  = 1.0;

        var cBuffer = gl.createBuffer();
        gl.bindBuffer(gl.ARRAY_BUFFER, cBuffer);
        gl.bufferData(gl.ARRAY_BUFFER, flatten(colors), gl.STATIC_DRAW);

        var colorLoc = gl.getAttribLocation(program, "aColor");
        gl.vertexAttribPointer(colorLoc, 4, gl.FLOAT, false, 0, 0);
        gl.enableVertexAttribArray(colorLoc);

        var vBuffer = gl.createBuffer();
        gl.bindBuffer(gl.ARRAY_BUFFER, vBuffer);
        gl.bufferData(gl.ARRAY_BUFFER, flatten(position), gl.STATIC_DRAW);

        var positionLoc = gl.getAttribLocation(program, "aPosition");
        gl.vertexAttribPointer(positionLoc, 4, gl.FLOAT, false, 0, 0);
        gl.enableVertexAttribArray(positionLoc);

        modelViewMatrixLoc = gl.getUniformLocation(program, "uModelViewMatrix");
        projectionMatrixLoc = gl.getUniformLocation(program, "uProjectionMatrix");

        document.getElementById("Button1").onclick = function(){ 
            phi = Math.PI/4;
            theta = Math.PI/4;
            perspect = 0;
            renderCube(radius*Math.sin(phi), radius*Math.sin(theta), radius*Math.cos(phi), perspect);
        }

        document.getElementById("Button2").onclick = function(){
            phi = Math.PI/4;
            theta = Math.PI/4;
            perspect = 1;
            renderCube(radius*Math.sin(phi), radius*Math.sin(theta), radius*Math.cos(phi), perspect);
        }

        document.getElementById("Button3").onclick = function(){
            phi = phi + 0.2;
            renderCube(radius*Math.sin(phi), radius*Math.sin(theta), radius*Math.cos(phi), perspect);
        }

        document.getElementById("Button4").onclick = function(){
            theta = theta + 0.2;
            renderCube(radius*Math.sin(phi), radius*Math.sin(theta), radius*Math.cos(phi), perspect);
        }
    }
}

interactiveViewer();