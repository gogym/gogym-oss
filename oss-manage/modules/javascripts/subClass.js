var Base = require('./baseClass');
var uitl = require('util');



function Extend() {

    /**
     * node.js自带的inherits是无法继承非原型方法和属性的，也就是说在父类构造函数内部定义的方法和属性子类中都无法得到继承，只有父类中的原型方法和属性才可以被子类继承（其实从源代码中就可以看出，inherits方法其实只复制了原型链而已）。那么我们如何实现内建属性和方法的继承呢？

     我们先来理解一下构造函数内建属性和方法的本质
     JS中this指针的本质是上下文对象的概念，其作用是在一个函数内部应用调用它对象的本身，那么我们只要将父类的上下文对象完全的复制给子类不就可以让子类调用父类构造函数中的内建属性和对象了嘛~~~
     Base.call(this)的作用为
     （1）在Extend中执行了Base的构造函数
     （2）执行base构造函数的时候其实真正的上下文对象为Extend的上下文对象

     */

    Base.call(this);
}

module.exports = Extend;




uitl.inherits(Extend,Base);//这句话就是实现了继承