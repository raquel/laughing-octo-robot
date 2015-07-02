'use strict';

/**
 * @ngdoc function
 * @name bmDashboardApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the bmDashboardApp
 */
angular.module('bmDashboardApp')
  .controller('AboutCtrl', function ($scope, $http) {
    $scope.bands = '';
    $http.get('http://localhost:8080/bm-services/bands').then(function(resp) {
     	console.log('Success', resp);
        $scope.bands = resp.data;
      }, function (err) {
        console.error('ERR', err);
        // err.status will contain the status code
      });
  });
