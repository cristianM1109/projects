function Calculator(numbers){

    var _numbers = numbers;

    function _sum(){
        var sum = 0;
        for(var i = 0; i < _numbers.length; i++){
        sum += _numbers[i];
    }
    return sum;
}

    this.average = function(){
        var length = _numbers.length;
        if(length > 0 ){
            return _sum() / length;
        }else{
            return 0;
        }
    };
}

var calculator = new Calculator([1,2,3,4,5]);
console.log(calculator.average());