let gl;
let theta = [0,0,0];
let thetaLoc;

var position = [];
var colors = [];

let s = 0.4;

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
    [1.0, 0.0, 0.0, 1.0],  // red
    [1.0, 1.0, 0.0, 1.0],  // yellow
    [0.0, 1.0, 0.0, 1.0],  // green
    [0.0, 0.0, 1.0, 1.0],  // blue
    [1.0, 0.0, 1.0, 1.0],  // magenta
    [0.0, 1.0, 1.0, 1.0],  // cyan
    [1.0, 1.0, 1.0, 1.0]   // white
];

const vertexShaderCode = `#version 300 es

in  vec4 aPosition;
in  vec4 aColor;
out vec4 vColor;

uniform vec3 uTheta;

void main()
{
    vec3 angles = radians(uTheta);
    vec3 c = cos(angles);
    vec3 s = sin(angles);

    mat4 rx = mat4(1.0,  0.0,  0.0, 0.0,
		    0.0,  c.x,  s.x, 0.0,
		    0.0, -s.x,  c.x, 0.0,
		    0.0,  0.0,  0.0, 1.0);
    mat4 ry = mat4(c.y, 0.0, -s.y, 0.0,
		    0.0, 1.0,  0.0, 0.0,
		    s.y, 0.0,  c.y, 0.0,
		    0.0, 0.0,  0.0, 1.0);
    mat4 rz = mat4(c.z, s.z, 0.0, 0.0,
		    -s.z,  c.z, 0.0, 0.0,
		    0.0,  0.0, 1.0, 0.0,
		    0.0,  0.0, 0.0, 1.0);

    vColor = aColor;
    gl_Position = rz * ry * rx * aPosition;
    gl_Position.z = -gl_Position.z;
}`;

const fragmentShaderCode = `#version 300 es

precision mediump float;

in vec4 vColor;
out vec4 fColor;

void
main()
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
    if (!gl.getShaderParameter(vertShader, gl.COMPILE_STATUS)) {
        console.error("Vertex shader compile error:\n" + gl.getShaderInfoLog(vertShader));
        return null;
    }

    var fragShader = gl.createShader(gl.FRAGMENT_SHADER);
    gl.shaderSource(fragShader, fsc);
    gl.compileShader(fragShader);
    if (!gl.getShaderParameter(fragShader, gl.COMPILE_STATUS)) {
        console.error("Fragment shader compile error:\n" + gl.getShaderInfoLog(fragShader));
        return null;
    }

    var shaderProgram = gl.createProgram();
    gl.attachShader(shaderProgram, vertShader);
    gl.attachShader(shaderProgram, fragShader);
    gl.linkProgram(shaderProgram);
    if (!gl.getProgramParameter(shaderProgram, gl.LINK_STATUS)) {
        console.error("Shader program link error:\n" + gl.getProgramInfoLog(shaderProgram));
        return null;
    }
    
    return shaderProgram;
}

function renderCube(){

    gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);
    
    theta[0] += -1.0;
    theta[1] += -3.0;
    theta[2] += -2.0;
    gl.uniform3fv(thetaLoc, theta);

    gl.drawArrays(gl.TRIANGLES, 0, position.length);
    requestAnimationFrame(renderCube);
}

window.onload = function init(){

    var canvas = document.getElementById('mycanvas');
    gl = canvas.getContext('webgl2');

    colorCube();
    gl.viewport(0, 0, canvas.width, canvas.height);
    gl.clearColor(1, 1, 1, 1);
    gl.enable(gl.DEPTH_TEST);

    var program = startShaders(gl, vertexShaderCode, fragmentShaderCode);
    gl.useProgram(program);

    var cBuffer = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, cBuffer);
    gl.bufferData(gl.ARRAY_BUFFER, flatten(colors), gl.STATIC_DRAW);

    var colorLoc = gl.getAttribLocation( program, "aColor" );
    gl.vertexAttribPointer( colorLoc, 4, gl.FLOAT, false, 0, 0 );
    gl.enableVertexAttribArray( colorLoc );

    var vBuffer = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, vBuffer);
    gl.bufferData(gl.ARRAY_BUFFER, flatten(position), gl.STATIC_DRAW);

    var positionLoc = gl.getAttribLocation(program, "aPosition");
    gl.vertexAttribPointer(positionLoc, 4, gl.FLOAT, false, 0, 0);
    gl.enableVertexAttribArray(positionLoc);

    thetaLoc = gl.getUniformLocation(program, "uTheta");

    requestAnimationFrame(renderCube);
}
