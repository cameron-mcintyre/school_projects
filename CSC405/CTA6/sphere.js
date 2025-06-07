"use strict";

var sphere = function() {
var canvas;
var gl;

var numTimesToSubdivide = 3;
var index = 0;

var positionsArray = [];
var normalsArray = [];

var va = vec4(0.0, 0.0, -1.0,1);
var vb = vec4(0.0, 0.942809, 0.333333, 1);
var vc = vec4(-0.816497, -0.471405, 0.333333, 1);
var vd = vec4(0.816497, -0.471405, 0.333333,1);

var near = 0.1;
var far = 3.0;
var radius = 1.5;
var theta = 0.0;
var phi = 0.0;

var fovy = 100;
var aspect = 1.0;

var va = vec4(0.0, 0.0, -1.0,1);
var vb = vec4(0.0, 0.942809, 0.333333, 1);
var vc = vec4(-0.816497, -0.471405, 0.333333, 1);
var vd = vec4(0.816497, -0.471405, 0.333333,1);

var lightAngleX = 1.0;
var lightAngleZ = 1.0;
var lightPosition = vec4(lightAngleX, 1.0, lightAngleZ, 1.0);
var lightAmbient = vec4(0.2, 0.2, 0.2, 0.2);
var lightDiffuse = vec4(1.0, 1.0, 1.0, 1.0);
var lightSpecular = vec4(1.0, 1.0, 1.0, 1.0);

var materialAmbient = vec4(0.1, 0.1, 0.3, 1.0);
var materialDiffuse = vec4(0.6, 0.7, 1.0, 1.0);
var materialSpecular = vec4(0.8, 0.8, 0.9, 1.0);
var materialShininess = 10.0;

var modelViewMatrix, projectionMatrix;
var modelViewMatrixLoc, projectionMatrixLoc;
var nMatrix, nMatrixLoc;

var eye;
var at = vec3(0.0, 0.0, 0.0);
var up = vec3(0.0, 1.0, 0.0);

const vertexShaderCode = `#version 300 es
in vec4 aPosition;
in vec4 aNormal;

out vec4 vColor;

uniform vec4 uAmbientProduct, uDiffuseProduct, uSpecularProduct;
uniform mat4 uModelViewMatrix;
uniform mat4 uProjectionMatrix;
uniform vec4 uLightPosition;
uniform float uShininess;
uniform mat3 uNormalMatrix;

void main() {
    vec3 pos = (uModelViewMatrix * aPosition).xyz;
    vec3 L;

    if(uLightPosition.w == 0.0) L = normalize(uLightPosition.xyz);
    else L = normalize( uLightPosition.xyz - pos );

    vec3 E = -normalize(pos);
    vec3 H = normalize(L + E);
    vec3 N = normalize( uNormalMatrix*aNormal.xyz);

    vec4 ambient = uAmbientProduct;

    float Kd = max(dot(L, N), 0.0 );
    vec4 diffuse = Kd*uDiffuseProduct;

    float Ks = pow(max(dot(N, H), 0.0), uShininess);
    vec4 specular = Ks * uSpecularProduct;

    if( dot(L, N) < 0.0 ) {
	      specular = vec4(0.0, 0.0, 0.0, 1.0);
    }

    gl_Position = uProjectionMatrix * uModelViewMatrix * aPosition;

    vColor = ambient + diffuse +specular;
    vColor.a = 1.0;
}`

const fragmentShaderCode = `#version 300 es
precision mediump float;
in vec4 vColor;
out vec4 fColor;

void
main()
{
    fColor = vColor;
}`

function renderTriangle(a, b, c) {

     positionsArray.push(a);
     positionsArray.push(b);
     positionsArray.push(c);
     normalsArray.push(vec4(a[0], a[1], a[2], 0.0));
     normalsArray.push(vec4(b[0], b[1], b[2], 0.0));
     normalsArray.push(vec4(c[0], c[1], c[2], 0.0));

     index += 3;
}

function divideTriangle(a, b, c, count) {
    if (count > 0) { //number of recursions

        //finding the middle points of each triangle vertex
        var ab = mix(a, b, 0.5);
        var ac = mix(a, c, 0.5);
        var bc = mix(b, c, 0.5);

        //normalizing those points to be at radius 1
        ab = normalize(ab, true);
        ac = normalize(ac, true);
        bc = normalize(bc, true);

        //continuing to divide into smaller triangles
        divideTriangle(a, ab, ac, count - 1);
        divideTriangle(ab, b, bc, count - 1);
        divideTriangle(bc, c, ac, count - 1);
        divideTriangle(ab, bc, ac, count - 1);
    }
    //render the triangles
    else {renderTriangle(a, b, c);}
}

function tetrahedron(a, b, c, d, n) {
    divideTriangle(a, b, c, n);
    divideTriangle(d, c, b, n);
    divideTriangle(a, d, b, n);
    divideTriangle(a, c, d, n);
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

window.onload = function init() {

    canvas = document.getElementById("mycanvas");
    gl = canvas.getContext('webgl2');

    gl.viewport(0, 0, canvas.width, canvas.height);
    gl.clearColor(0.5, 0.5, 0.5, 0.6);
    gl.enable(gl.DEPTH_TEST);

    var program = startShaders(gl, vertexShaderCode, fragmentShaderCode);
    gl.useProgram(program);

    var ambientProduct = mult(lightAmbient, materialAmbient);
    var diffuseProduct = mult(lightDiffuse, materialDiffuse);
    var specularProduct = mult(lightSpecular, materialSpecular);

    tetrahedron(va, vb, vc, vd, numTimesToSubdivide);

    var nBuffer = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, nBuffer);
    gl.bufferData(gl.ARRAY_BUFFER, flatten(normalsArray), gl.STATIC_DRAW);

    var normalLoc = gl.getAttribLocation(program, "aNormal");
    gl.vertexAttribPointer(normalLoc, 4, gl.FLOAT, false, 0, 0);
    gl.enableVertexAttribArray(normalLoc);

    var vBuffer = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, vBuffer);
    gl.bufferData(gl.ARRAY_BUFFER, flatten(positionsArray), gl.STATIC_DRAW);

    var positionLoc = gl.getAttribLocation(program, "aPosition");
    gl.vertexAttribPointer(positionLoc, 4, gl.FLOAT, false, 0, 0);
    gl.enableVertexAttribArray(positionLoc);

    modelViewMatrixLoc = gl.getUniformLocation(program, "uModelViewMatrix");
    projectionMatrixLoc = gl.getUniformLocation(program, "uProjectionMatrix");
    nMatrixLoc = gl.getUniformLocation(program, "uNormalMatrix");

    document.getElementById("Button0").onclick = function() {radius *= 2.0;};
    document.getElementById("Button1").onclick = function() {radius *= 0.5;};

    document.getElementById("Button2").onclick = function() {
        numTimesToSubdivide++;
        index = 0;
        positionsArray = [];
        normalsArray = [];
        init();
    };

    document.getElementById("Button3").onclick = function() {
        if(numTimesToSubdivide) numTimesToSubdivide--;
        index = 0;
        positionsArray = [];
        normalsArray = [];
        init();
    };

    document.getElementById("Button4").onclick = function() {
        var angle = radians(10);

        var x = lightAngleX;
        var z = lightAngleZ;
        lightAngleX = x * Math.cos(angle) - z * Math.sin(angle);
        lightAngleZ = x * Math.sin(angle) + z * Math.cos(angle);

        lightPosition = vec4(lightAngleX, 1.0, lightAngleZ, 1.0);
        gl.uniform4fv(gl.getUniformLocation(program, "uLightPosition"), lightPosition);
    };

    document.getElementById("Button5").onclick = function() {
        var angle = radians(-10);

        var x = lightAngleX;
        var z = lightAngleZ;
        lightAngleX = x * Math.cos(angle) - z * Math.sin(angle);
        lightAngleZ = x * Math.sin(angle) + z * Math.cos(angle);

        lightPosition = vec4(lightAngleX, 1.0, lightAngleZ, 1.0);
        gl.uniform4fv(gl.getUniformLocation(program, "uLightPosition"), lightPosition);
    };

    gl.uniform4fv(gl.getUniformLocation(program, "uAmbientProduct"),ambientProduct);
    gl.uniform4fv(gl.getUniformLocation(program, "uDiffuseProduct"),diffuseProduct);
    gl.uniform4fv(gl.getUniformLocation(program, "uSpecularProduct"), specularProduct);
    gl.uniform4fv(gl.getUniformLocation(program, "uLightPosition"), lightPosition);
    gl.uniform1f(gl.getUniformLocation(program, "uShininess"),materialShininess);

    render();
}

function render() {

    gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

    eye = vec3(radius*Math.sin(theta)*Math.cos(phi),
        radius*Math.sin(theta)*Math.sin(phi), radius*Math.cos(theta));

    modelViewMatrix = lookAt(eye, at , up);
    projectionMatrix = perspective(fovy, aspect, near, far);

    nMatrix = normalMatrix(modelViewMatrix, true);

    gl.uniformMatrix4fv(modelViewMatrixLoc, false, flatten(modelViewMatrix));
    gl.uniformMatrix4fv(projectionMatrixLoc, false, flatten(projectionMatrix));
    gl.uniformMatrix3fv(nMatrixLoc, false, flatten(nMatrix));

    for(var i = 0; i  <index; i += 3)
        gl.drawArrays(gl.TRIANGLES, i, 3);

    requestAnimationFrame(render);
}

}

sphere();