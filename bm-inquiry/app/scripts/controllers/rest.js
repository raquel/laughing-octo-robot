'use strict';

/**
 * @ngdoc function
 * @name bmInquiryApp.controller:RestCtrl
 * @description
 * # RestCtrl
 * Controller of the uiApp
 */

// var baseUrl = 'http://localhost\\:8080/krustyburger-api';
var baseUrl = 'http://localhost:8080/bm-services/save';

angular.module('bmInquiryApp')
  .factory('ProductService', function ($resource){
    var resource = $resource(baseUrl+'/produto/:operation/:id', { id: 0 }, {});
    resource.retrieveProduct = function (productId) { return this.get({operation: 'retrieve', id: productId}); };
    resource.retrieveProduct = function (productIdsArray) { return this.query({ operation: 'retrievearray', 'idsArray[]': productIdsArray }); };
    resource.storeProduct = function (product) { return this.save({ operation: 'store', id: product.id, nome: product.nome, categoria: product.categoria, preco: product.preco }); };
    resource.eraseProduct = function (productId) { return this.delete({ operation: 'erase', id: productId }); };
    return resource;
  })
  .factory('ProductResource', function ($resource) {
    ///save/{email}/{name}/{bands}
    var resource = $resource(baseUrl+'/:operation', { email: '@email', name: '@name'});
    resource.prototype.retrieve = function () { return this.$get({ operation: 'save' }); };
    resource.prototype.erase = function () { return this.$delete({ operation: 'save' }); };
    resource.prototype.store = function () {
      var thisInst = this;
      return resource.save(
        { operation: 'save', email: this.email, name: this.name},
        function (value) { angular.copy(value, thisInst); }).$promise;
    };
    return resource;
  })
  .controller('RestCtrl', function ($scope, $resource, $http, ProductService, ProductResource, jsonFilter) {
    $http.defaults.useXDomain = true;

    //passa os objetos como query params
    $scope.getWithParams = function () {
      var callURL = (baseUrl);//TODO alterar para URL real
      var serverResource = $resource(callURL);//TODO alterar para pegar do form
      var getConfig = {};
      if ($scope.newID !== undefined && $scope.newID !== '') {
        getConfig.id = $scope.newID;
      }
      serverResource.get(getConfig,
        function (value, responseHeaders) {
          $scope.getWithParamsResult = 'GET SUCCESS\n\nvalue: ' + jsonFilter(value) + '\n\nresponseHeaders: ' + jsonFilter(responseHeaders());
        });
    };

    $scope.instGetCall = function () {
        var product = new ProductResource();
        if ($scope.newID !== undefined && $scope.newID !== '') {
            product.id = $scope.newID;
        } else {
            product.id = 1;
        }
        product.retrieve();
        $scope.instCallResult = product;
    };

    $scope.instSaveCall = function () {
        var product = new ProductResource();
        product.email = '31';
        product.name = 'Doe';
        product.store();
        $scope.instCallResult = product;
    };

    $scope.instDeleteCall = function () {
        var product = new ProductResource();
        product.id = 2;
        product.erase();
        $scope.instCallResult = product;
    };

    $scope.instPutCall = function () {
        var product = new ProductResource();
        product.name = 3;
        product.email = 'NEW_PICTURE_DATA';
        product.updatePicture();
        $scope.instCallResult = product;
    };

  });
