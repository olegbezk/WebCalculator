var calculatorApp = angular.module('calculatorApp', ['calculatorModule']);
var calculatorModule = angular.module('calculatorModule', []);

calculatorModule.controller('calculatorController', function($scope, $http) {

    function calculate() {
        if(calculatorModel.singleOp == true) {
                    postData();
                    calculatorModel.singleOp = false;
                    calculatorModel.newSign = false;
                    calculatorModel.continueOperation = true;
                    showHistory();
                }
    }

    function showHistory() {
        $http.get('http://localhost:8080/transaction-log/logs/')
            .then(function(res){
                for(var i = 0; i < res.data.length; i++) {
                    res.data[i].arithmeticOperation = convertToJsonData(res.data[i].arithmeticOperation);
                }
                $scope.results = res.data;
            });
    }

    function postData() {

        substituteOperationSign();

        var calcData = {
            leftOperand: calculatorModel.firstNumber,
            rightOperand: calculatorModel.secondNumber,
            arithmeticOperation: calculatorModel.subOperation
            };

        $http.post(
            'http://localhost:8080/transaction-log/add/',
            JSON.stringify(calcData),
            {
                headers: {
                    'Content-Type': 'application/json'
                }
            }
        ).success(function (response) {
            $scope.calcResult = response;
            calculatorModel.currentDisplay = response.result;
            calculatorModel.result = response.result;
        }).error(function (response) {
                alert("Division by Zero !")
                calculatorModel.reset();
            });
    }

    function substituteOperationSign() {
        switch(calculatorModel.operation) {
            case "+":
                calculatorModel.subOperation = "ADD";
                break;
            case "-":
                calculatorModel.subOperation = "SUBTRACT";
                break;
            case "*":
                calculatorModel.subOperation = "MULTIPLY";
                break;
            case "/":
                calculatorModel.subOperation = "DIVIDE";
                break;
            case "^":
                calculatorModel.subOperation = "POWER";
                break;
        }
    }

    function convertToJsonData(sub) {
         switch(sub) {
                     case "ADD": return "+";
                     case "SUBTRACT": return "-";
                     case "MULTIPLY": return "*";
                     case "DIVIDE": return "/";
                     case "POWER" : return "^";
                 }
         return "UNKNOWN";
    }

    $scope.calculator = calculatorModel;

    $scope.numberButtonClicked = function(clickedNumber) {

        if(calculatorModel.currentNumber === "0") {
            calculatorModel.currentNumber = "";
        }

        if(calculatorModel.continueOperation) {
            calculatorModel.firstNumber = calculatorModel.result;
            calculatorModel.currentNumber += clickedNumber;
            calculatorModel.continueOperation = false;
        } else {
            calculatorModel.currentNumber += clickedNumber;
        }

        if(calculatorModel.operation == "") {
            calculatorModel.firstNumber = parseFloat(calculatorModel.currentNumber);
        } else {
            calculatorModel.secondNumber = parseFloat(calculatorModel.currentNumber);
            calculatorModel.singleOp = true;
        }

        calculatorModel.currentDisplay += clickedNumber;
    };

    $scope.operationButtonClicked = function(clickedOperation) {

        if(calculatorModel.singleOp == true) {
            calculate();
        }
        calculatorModel.setOperation(clickedOperation);
    };

    $scope.enterClicked = function() {
        calculate();
    };

    $scope.resetClicked = function() {
        calculatorModel.reset();
        showHistory();
    };
});

calculatorModel = {

    result: 0,
    operation: "",
    subOperation: "",
    currentNumber: "0",
    currentDisplay: "",
    currentDisplayHolder: "",
    firstNumber: 0,
    secondNumber: 0,
    singleOp: false,
    continueOperation: false,
    newSign: false,

    reset: function() {
        this.result = 0;
        this.operation = "";
        this.currentNumber = "0";
        this.currentDisplay = "" ;
        this.firstNumber = 0;
        this.secondNumber = 0;
        this.continueOperation = false;
        this.newSign = false;
        this.currentDisplayHolder = "";
    },

    setOperation: function(operationToSet) {

        this.operation = operationToSet;

        if(this.newSign == false) {
            this.currentDisplayHolder = this.currentDisplay;
            this.newSign = true;
        } else if(this.continueOperation == true) {
            this.currentDisplayHolder = this.result;
        }

        if(this.currentNumber === "0") {
            this.currentDisplay += "0";
        }

        if(this.newSign == true) {
            this.currentDisplay = this.currentDisplayHolder + " " + this.operation + " ";
        }

        this.currentNumber = "0";
    }

};