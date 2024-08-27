function Person(name,age){
    var _name = name;
    var _age = age;
    this.public_age = age;

    function _get_birth_year(){
        var year = new Date().getFullYear();
        return year - _age;
    }

    this.get_info = function (){
        return _name + " is " + _age + " years old."
    }

    this.getBirthYear = function(){
        return _name + "'s birth year is "+ _get_birth_year()+ ".";
    }
}

var john = new Person("John",26);

console.log(john.public_age);


