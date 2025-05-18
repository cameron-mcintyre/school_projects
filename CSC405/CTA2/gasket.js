
function vec2(x, y) { return [x, y]; }
function add(a, b) { return [a[0] + b[0], a[1] + b[1]]; }
function mult(c, d) { return [c * d[0], c * d[1]]; }

//do something, i'm not sure what
var canvas = document.getElementById('mycanvas');
var gl = canvas.getContext('webgl');

//build vertex shader
var vertCode = 'attribute vec2 coordinates;' + 'void main(void) {' + 'gl_PointSize = 2.0;' + 'gl_Position = vec4(coordinates,0.0, 1.0);' + '}';
var vertShader = gl.createShader(gl.VERTEX_SHADER);
gl.shaderSource(vertShader, vertCode);
gl.compileShader(vertShader);

//build fragment shader
var fragCode = 'void main(void) {' + 'gl_FragColor = vec4(0.0, 0.0, 0.5, 1.0);' + '}';
var fragShader = gl.createShader(gl.FRAGMENT_SHADER);
gl.shaderSource(fragShader, fragCode);
gl.compileShader(fragShader);

//attach the vertex and fragment shaders to the program
var shaderProgram = gl.createProgram();
gl.attachShader(shaderProgram, vertShader);
gl.attachShader(shaderProgram, fragShader);
gl.linkProgram(shaderProgram);
gl.useProgram(shaderProgram);

//some points
var numPoints = 20000000;
var points = [];
var vertices = [vec2(-0.9, -0.9), vec2(0.0, 0.9), vec2(0.9, -0.9)]

var u = mult(0.5, add(vertices[0], vertices[1]));
var v = mult(0.5, add(vertices[0], vertices[2]));
var p = mult(0.5, add(u, v));

points.push(p);

//randomly build the points in the triangle
for(var i = 0; i < numPoints - 1; i++){
    var j = Math.floor(Math.random() * 3);
    p = mult(0.5, add(points[i], vertices[j]));
    points.push(p);
}

//flatten the points into x,y
var flattenedPoints = [];
for(var i = 0; i < points.length; i++){
    flattenedPoints.push(points[i][0], points[i][1]);
}

//buffer for the points
var vertex_buffer = gl.createBuffer();
gl.bindBuffer(gl.ARRAY_BUFFER, vertex_buffer);
gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(flattenedPoints), gl.STATIC_DRAW);

//set up attributes (what are these?)
var coord = gl.getAttribLocation(shaderProgram, "coordinates");
gl.vertexAttribPointer(coord, 2, gl.FLOAT, false, 0, 0);
gl.enableVertexAttribArray(coord);

//set up canvas with some default stuff
gl.clearColor(0.9, 0.9, 0.9, 1.0);
gl.clear(gl.COLOR_BUFFER_BIT);
gl.viewport(0, 0, canvas.width, canvas.height);

//draw points
gl.bindBuffer(gl.ARRAY_BUFFER, vertex_buffer);
gl.drawArrays(gl.POINTS, 0, numPoints);