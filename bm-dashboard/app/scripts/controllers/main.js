'use strict';

/**
 * @ngdoc function
 * @name bmDashboardApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the bmDashboardApp
 */
angular.module('bmDashboardApp')
  .controller('MainCtrl', function ($scope) {
    $scope.bands = '';
    $scope.alerts = [];

    $scope.closeAlert = function (index) {
        $scope.alerts.splice(index, 1);
    };

    var ws = new WebSocket('ws://localhost:8080/bm-services/ranking');
    ws.onmessage = function (message) {
        var receivedData = message.data;
        var parsedData = JSON.parse(receivedData);
        $scope.bands = parsedData;
    };
    ws.onopen = function () {
        $scope.$apply(function () {
            $scope.alerts.push({
                type: 'info',
                msg: 'Push connection from server is working'
            });
        });
    };
    ws.onclose = function () {
        $scope.$apply(function () {
            $scope.alerts.push({
                type: 'warning',
                msg: 'Error on push connection from server '
            });
        });
    };
  });

	//  var ws = new WebSocket("ws://localhost:8080/bm-services/ranking");
	//  ws.onopen = function()
	//  {
	// 	alert("Web Socket is connected!!");			        
	//  };
	//  ws.onmessage = function (evt) 
	//  { 			     	
	// 	var msg = evt.data;
	// 	alert("Message received:" +  msg);
	//  };
	//  ws.onclose = function()
	//  { 
	// 	alert("Connection is closed..."); 
	//  };
	// function sendMessage() {
	// ws.send("Message to server from client");
	// }
		