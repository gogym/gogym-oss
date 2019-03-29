module.exports=Base;

function Base() {
    //构造方法内的属性和方法
    this.name={};
    this.showName=function () {
        console.log(this.name);
    };

}

//定义属性和方法
Base.prototype.name1={};
Base.prototype.showName1=function () {
    console.log(this.name1);
};

