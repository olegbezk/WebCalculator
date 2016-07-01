var calculatorApp = angular.module('calculatorApp', ['calculatorModule']);
var calculatorModule = angular.module('calculatorModule', []);

calculatorModule.controller('calculatorController', function($scope, $http) {

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
            calculatorModel.currentDisplay = "";
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
        calculatorModel.setOperation(clickedOperation);
    };

    $scope.enterClicked = function() {
        if(calculatorModel.singleOp == true) {
            postData();
            calculatorModel.operation = "";
            calculatorModel.singleOp = false;
            calculatorModel.continueOperation = true;
            showHistory();
        }
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
    firstNumber: 0,
    secondNumber: 0,
    singleOp: false,
    continueOperation: false,

    reset: function() {
        this.result = 0;
        this.operation = "";
        this.currentNumber = "0";
        this.currentDisplay = "" ;
        this.firstNumber = 0;
        this.secondNumber = 0;
        this.continueOperation = false;
    },

    setOperation: function(operationToSet) {

        if(this.operation == "") {
                this.operation = operationToSet;
            }

        if(this.currentNumber === "0") {
            this.currentDisplay += "0";
        }

        if(this.operation != "") {
            this.currentDisplay += " " + this.operation + " ";
        }

        this.currentNumber = 0;
    }

};