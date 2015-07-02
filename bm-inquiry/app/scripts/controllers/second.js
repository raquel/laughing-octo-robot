'use strict';

/**
 * @ngdoc function
 * @name bmInquiryApp.controller:SecCtrl
 * @description
 * # SecCtrl
 * Controller of the bmInquiryApp
 */
angular.module('bmInquiryApp')
  .controller('SecCtrl', function ($scope, $http) {
  	$scope.bands = '';
    $http.get('http://localhost:8080/bm-services/bands').then(function(resp) {
     	console.log('Success', resp);
        $scope.bands = resp.data;
      }, function (err) {console.error('ERR', err);
    });
    $scope.addRowAsyncAsJSON = function(){
      var dataObj = {
        email : 'email@mail.com',
        name : 'teste',
        bands : ''
      };  
      var res = $http.post('http://localhost:8080/bm-services/saves', dataObj);
      res.success(function(data, status, headers, config) {
        $scope.message = data;
        console.log(config, headers, status);
      });
      res.error(function(data, status, headers, config) {
        console.error('ERROR' + JSON.stringify({data: data}), status, headers, config);
        //alert( "failure message: " + JSON.stringify({data: data}));
      });   
      $scope.name='';
      $scope.email='';
      $scope.bands='';
   };
  });
