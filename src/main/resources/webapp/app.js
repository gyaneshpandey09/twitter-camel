'use strict';

var myApp = angular.module('myApp', ['ngRoute']);

myApp.config(function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "search.html",
            controller: "searchController"
        })
        .when("/search", {
            templateUrl: "search.html",
            controller: "searchController"
        })
        .when("/searchGeo", {
            templateUrl: "searchGeo.html",
            controller: "searchGeoController"
        })
        .when("/postTweet", {
            templateUrl: "postTweet.html",
            controller: "postController"
        })
        .when("/trends", {
            templateUrl: "trends.html",
            controller: "trendsController"
        });
});

myApp.controller('searchController', function () {
    console.log('searchController starting');
});

myApp.controller('searchGeoController', function ($scope) {
    console.log('searchGeoController starting');
});

myApp.controller('postController', function ($scope) {
    console.log('postController starting');
});

myApp.controller('trendsController', function ($scope) {
	getTrends();
    console.log('trendsController starting');
});