var calculatorApp = angular.module('calculatorApp', ['calculatorModule']);
var calculatorModule = angular.module('calculatorModule', []);

calculatorModule.controller('calculatorController', function($scope, $http) {

    var singleOp = false;

    function showHistory() {
        $http.get('http://localhost:8080/transaction-log/logs/')
            .then(function(res){
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

    $scope.calculator = calculatorModel;

    $scope.numberButtonClicked = function(clickedNumber) {
        if(calculatorModel.currentNumber === "0") {
            calculatorModel.currentNumber = "";
            calculatorModel.currentDisplay = "";
        }

        calculatorModel.currentNumber += clickedNumber;

        if(calculatorModel.operation == "") {
            calculatorModel.firstNumber = parseFloat(clickedNumber);
        } else {
            calculatorModel.secondNumber = parseFloat(clickedNumber);
            singleOp = true;
        }
        calculatorModel.currentDisplay += clickedNumber;
    };

    $scope.operationButtonClicked = function(clickedOperation) {
        calculatorModel.setOperation(clickedOperation);
    };

    $scope.enterClicked = function() {
        if(singleOp == true) {
            postData();
            showHistory();
            singleOp = false;
        }
    };

    $scope.resetClicked = function() {
        calculatorModel.reset();
    };
});

calculatorModel = {
    result: 0,
    operation: "",
    subOperation: "",
    currentNumber: "0",
    currentDisplay: "",
    firstNumber: 0,
    secondNumber: 0,

    reset: function() {
        this.result = 0;
        this.operation = "";
        this.currentNumber = "0";
        this.currentDisplay = "" ;
        this.firstNumber = 0;
        this.secondNumber = 0;
    },

    setOperation: function(operationToSet) {
        this.operation = operationToSet;
        if(calculatorModel.currentNumber === "0") {
            this.currentDisplay += "0";
        }

        this.currentDisplay += " " + this.operation + " ";
        //this.calculate();

        this.currentNumber = "";
    }

};