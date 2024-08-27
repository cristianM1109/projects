function Animal(name){
    this.name = name;
}


Animal.prototype.sayName = function (){
    console.log("My name is "+ this.name+".");
}

var animal = new Animal("Animal");
animal.sayName();

function Dog(name,breed){
    Animal.call(this,name);
    this.breed=breed;
}

Dog.prototype = Object.create(Animal.prototype);

var caine = new Dog("Rex","Husky");
caine.sayName();

Dog.prototype.sayName = function (){
    console.log("Woof!My name is "+ this.name+"and I am a "+this.breed+".");
}

var dog = new Dog("Puliput","Labrador");
Dog.prototype.constructor=Dog;
dog.sayName();