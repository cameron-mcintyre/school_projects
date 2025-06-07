const vertexShaderRegularCode = `#version 300 es

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

const vertexShaderFancyCode = `#version 300 es

in vec4 aPosition;
in vec4 aNormal;
out vec4 vColor;

uniform vec4 ambientProduct, diffuseProduct, specularProduct;
uniform mat4 modelViewMatrix;
uniform mat4 projectionMatrix;
uniform vec4 lightPosition;
uniform float shininess;
uniform mat3 normalMatrix;

void main()
{
    vec3 pos = (modelViewMatrix * aPosition).xyz;
    vec3 light = lightPosition.xyz;
    vec3 L = normalize( light - pos );


    vec3 E = normalize(-pos);
    vec3 H = normalize(L + E);
    vec3 N = normalize(normalMatrix*aNormal.xyz);

    vec4 ambient = ambientProduct;

    float Kd = max(dot(L, N), 0.0);
    vec4  diffuse = Kd*diffuseProduct;

    float Ks = pow(max(dot(N, H), 0.0), shininess);
    vec4 specular = Ks * specularProduct;

    if(dot(L, N) < 0.0){
        specular = vec4(0.0, 0.0, 0.0, 1.0);
    }

    gl_Position = projectionMatrix * modelViewMatrix * aPosition;

    vColor = ambient + diffuse + specular;
    vColor.a = 1.0;
}`

const fragmentShaderCode = `#version 300 es

precision mediump float;

in vec4 vColor;
out vec4 fColor;

void main()
{
    fColor = vColor;
}`;

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