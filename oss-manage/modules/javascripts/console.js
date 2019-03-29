
module.exports=Print;

function Print(name) {
    console.log(name);
}

Print.prototype.obj = {
    reduce:function(a,b){
        return  a - b;
    },
    add:function(a,b){
        console.log("我是obj的add方法：");
        console.log(a+b);
    }
}
