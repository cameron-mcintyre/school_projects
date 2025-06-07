var paintersAlgo = function(){

    var canvas = document.getElementById('mycanvas');
    var gl = canvas.getContext('webgl2');
    var aPosition;
    var aColor;
    var numTris = 6;
    var perspectiveView;
    var projection;
    var modelView;
    var eye = vec3(1.0, 1.5, 1.5);
    var at = vec3(0.0, 0.0, 0.0);
    var up = vec3(0.0, 1.0, 0.0);
    var fovy = 45;
    var aspect = canvas.width/canvas.height;
    var near = 0.1;
    var far = 10.0;

    var vertices = [
        //shape 1 - green
        vec4(-0.8, -0.5, -1.2, 1.0),
        vec4( 0.0,  0.6, -1.2, 1.0),
        vec4( 0.8, -0.5, -1.2, 1.0),
        //shape 2 - red
        vec4(-0.4, -0.5, -1.0, 1.0),
        vec4( 0.2,  0.1, -1.5, 1.0),
        vec4( 0.6, -0.3, -1.5, 1.0),
        //shape 3 - dark blue
        vec4(-0.2,  0.3, -0.2, 1.0),
        vec4( 0.4,  0.9, -0.1, 1.0),
        vec4( 0.6,  0.2, -0.4, 1.0),
        //shape 4 - yellow
        vec4(-0.7,  0.2, -0.3, 1.0),
        vec4(-0.2,  0.6, -0.5, 1.0),
        vec4(-0.1, -0.1, -0.4, 1.0),
        //shape 5 - pink
        vec4( 0.3, -0.6, -0.3, 1.0),
        vec4( 0.8, -0.4, -0.7, 1.0),
        vec4( 0.4, -0.2,  0.1, 1.0),
        //shape 6 - white
        vec4(-0.3, -0.1,  0.1, 1.0),
        vec4( 0.1,  0.2,  0.5, 1.0),
        vec4( 0.5, -0.2,  0.0, 1.0)];

    var newVertices = []; //use the painter's algorithm to sort the vertices array into this new array.

    var colors = [
        //shape 1 - green
        vec4(0.0, 1.0, 0.0, 1.0),
        vec4(0.0, 1.0, 0.0, 1.0),
        vec4(0.0, 1.0, 0.0, 1.0),
        //shape 2 - red
        vec4(1.0, 0.0, 0.0, 1.0),
        vec4(1.0, 0.0, 0.0, 1.0),
        vec4(1.0, 0.0, 0.0, 1.0),
        //shape 3 - blue
        vec4(0.0, 0.0, 1.0, 1.0),
        vec4(0.0, 0.0, 1.0, 1.0),
        vec4(0.0, 0.0, 1.0, 1.0),
        //shape 4 - yellow
        vec4(1.0, 1.0, 0.0, 1.0),
        vec4(1.0, 1.0, 0.0, 1.0),
        vec4(1.0, 1.0, 0.0, 1.0),
        //shape 5 - pink
        vec4(1.0, 0.0, 1.0, 1.0),
        vec4(1.0, 0.0, 1.0, 1.0),
        vec4(1.0, 0.0, 1.0, 1.0),
        //shape 6 - white
        vec4(1.0, 1.0, 1.0, 1.0),
        vec4(1.0, 1.0, 1.0, 1.0),
        vec4(1.0, 1.0, 1.0, 1.0)];

    var newColors = []; //sort the colors along with the vertices, or the colors get all swapped around.

    var vertexShader = `#version 300 es
        in vec4 aPosition;
        in vec4 aColor;
        uniform mat4 uModelViewProjection;
        out vec4 vColor;

        void main()
        {
            gl_Position = uModelViewProjection * aPosition;
            vColor = aColor;
        }
    `;

    var fragmentShader = `#version 300 es
        precision mediump float;
        in vec4 vColor;
        out vec4 fColor;

        void
        main()
        {
            fColor = vColor;
        }
    `;

    //painter's algorithm - puts the triangle vertices and colors into a new array, sorted by the average Z values per tri.
    function paintersAlgorithm(vertices, colors){

        var numTris = (vertices.length) / 3; //each tri uses 3 vec4s.
        var trianglesWithZs = []; //temp array for tris during sort.
        var avgZ; //average Z val per tri.
        var triStart; //temp var to make it easier to pick out each tri from the first array.

        for(i = 0; i < numTris; i++){ //loop to push a tri along with the Z avg into the temp array.
            triStart = i * 3;
            avgZ = (vertices[triStart][2] + vertices[triStart + 1][2] + vertices[triStart + 2][2]) / 3;
            trianglesWithZs.push({
                verts: [vertices[triStart], vertices[triStart + 1], vertices[triStart + 2]], 
                colors: [colors[triStart], colors[triStart + 1], colors[triStart + 2]],
                avgZ: avgZ});
            avgZ = 0;
        }

        trianglesWithZs.sort(function(a, b) { //small sort function.
            return a.avgZ - b.avgZ;
        });

        for(var i = 0; i < trianglesWithZs.length; i++){ //pushing the sorted temp array into the two new arrays
            var tri = trianglesWithZs[i];
            newVertices.push(tri.verts[0]);
            newVertices.push(tri.verts[1]);
            newVertices.push(tri.verts[2]);
            newColors.push(tri.colors[0]);
            newColors.push(tri.colors[1]);
            newColors.push(tri.colors[2]);
        }

        return;
    }

    function render(vertRender, colorRender){ //hitting the button runs this function

        gl.bindBuffer(gl.ARRAY_BUFFER, vBuffer); //vertex buffer
        gl.bufferData(gl.ARRAY_BUFFER, flatten(vertRender), gl.STATIC_DRAW);
        gl.vertexAttribPointer(aPosition, 4, gl.FLOAT, false, 0, 0);
        gl.enableVertexAttribArray(aPosition);
        gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

        gl.bindBuffer(gl.ARRAY_BUFFER, cBuffer); //color buffer
        gl.bufferData(gl.ARRAY_BUFFER, flatten(colorRender), gl.STATIC_DRAW);
        aColor = gl.getAttribLocation(program, 'aColor');
        gl.vertexAttribPointer(aColor, 4, gl.FLOAT, false, 0, 0);
        gl.enableVertexAttribArray(aColor);

        numTris = vertRender.length / 3; //render each tri using a loop, in case we want to add more tris.
        for (i = 0; i < numTris; i++) {
            gl.drawArrays(gl.TRIANGLES, i * 3, 3);
        }
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
        
        return shaderProgram;}

    window.onload = function init(){

        gl.viewport(0, 0, canvas.width, canvas.height);
        gl.clearColor(0.8, 0.8, 0.8, 1.0);

        program = startShaders(gl, vertexShader, fragmentShader);
        gl.useProgram(program);

        vBuffer = gl.createBuffer();
        gl.bindBuffer(gl.ARRAY_BUFFER, vBuffer);
        gl.bufferData(gl.ARRAY_BUFFER, flatten(vertices), gl.STATIC_DRAW);
        aPosition = gl.getAttribLocation(program, 'aPosition');
        gl.vertexAttribPointer(aPosition, 4, gl.FLOAT, false, 0, 0);
        gl.enableVertexAttribArray(aPosition);

        cBuffer = gl.createBuffer();
        gl.bindBuffer(gl.ARRAY_BUFFER, cBuffer);
        gl.bufferData(gl.ARRAY_BUFFER, flatten(colors), gl.STATIC_DRAW);
        aColor = gl.getAttribLocation(program, 'aColor');
        gl.vertexAttribPointer(aColor, 4, gl.FLOAT, false, 0, 0);
        gl.enableVertexAttribArray(aColor);

        perspectiveView = gl.getUniformLocation(program, 'uModelViewProjection');
        
        projection = perspective(fovy, aspect, near, far);
        modelView = lookAt(eye, at, up);
        modelViewProjection = mult(projection, modelView);
        
        gl.uniformMatrix4fv(perspectiveView, false, flatten(modelViewProjection));
        gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);
        
        for (i = 0; i < numTris; i++) {
                gl.drawArrays(gl.TRIANGLES, i * 3, 3);
        }

        document.getElementById("Button1").onclick = function(){
            paintersAlgorithm(vertices, colors);
            render(newVertices, newColors);
        }
    };
};

paintersAlgo();