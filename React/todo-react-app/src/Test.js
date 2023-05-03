export function exampleFunction(){
    return new Promise((resolve,reject)=>{
        var oReq = new XMLHttpRequest();
        oReq.open("GET","http://localhost:8080/todo");
        oReq.onload = function () {
            resolve(oReq.response);
        };
        oReq.onerror = function(){
            reject(oReq.response);
        };
        oReq.send();
    });
}

