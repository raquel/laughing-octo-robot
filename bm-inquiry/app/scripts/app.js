'use strict';

/**
 * @ngdoc overview
 * @name bmInquiryApp
 * @description
 * # bmInquiryApp
 *
 * Main module of the application.
 */
angular
  .module('bmInquiryApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/second', {
        templateUrl: 'views/second.html',
        controller: 'SecCtrl'
      })
      .when('/third', {
        templateUrl: 'views/third.html',
        controller: 'SecCtrl'
      })
      .when('/rest', {
        templateUrl: 'views/rest.html',
        controller: 'RestCtrl'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
