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
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
