"use strict";

var mainApp = angular.module("mainApp", ['ngRoute', 'ngStorage']);

mainApp.controller("navbarController", function($scope, $location) {
    //alert($location.path());
});

mainApp.config(function($routeProvider) {
    $routeProvider
        .when('/home', {
            templateUrl: 'html/home/home.html',
            controller: 'LoginController'
        })
        .when('/contact-form', {
            templateUrl: 'html/contact-form/index.html',
            controller: 'StudentController'
        })
        .when('/vacancies', {
            templateUrl: 'html/vacancies/index.html',
            controller: 'VacanciesController'
        })
        .when('/employer', {
            templateUrl: 'html/employer/index.html',
            controller: 'VacancyController'
        })
        .when('/vacancy-description', {
            templateUrl: 'html/vacancy_description/index.html',
            controller: 'VacancyController'
        })
        .when('/about_us', {
            templateUrl: 'html/about_us/index.html',
            controller: 'VacancyController'
        })
        .when('/for-employer', {
            templateUrl: 'html/for-employer/index.html',
            controller: 'VacancyController'
        })
        .when('/login', {
            templateUrl: 'html/login.html',
            controller: 'LoginController'
        })
        .when('/register', {
            templateUrl: 'html/regi-option.html',
            controller: 'VacancyController'
        })
        .when('/forgot', {
            templateUrl: 'html/forgot.html',
            controller: 'VacancyController'
        })
        .when('/recruiter-profile', {
            templateUrl: 'html/recruiter-profile/recruiter_profile.html',
            controller: 'VacancyController'
        })
        .when('/regi-option', {
            templateUrl: 'html/regi-option.html',
            controller: 'VacancyController'
        })
        .when('/cand-regi', {
            templateUrl: 'html/cand-regi.html',
            controller: 'VacancyController'
        })
        .when('/emp-regi', {
            templateUrl: 'html/emp-regi.html',
            controller: 'VacancyController'
        })
        .when('/interviewer-profile', {
            templateUrl: 'html/interviewer-profile/index.html',
            controller: 'VacancyController'
        })
        .when('/candidate-profile', {
            templateUrl: 'html/candidate-profile/index.html',
            controller: 'VacancyController'
        })
        .when('/candidate-search', {
            templateUrl: 'html/candidate-search/index.html',
            controller: 'VacancyController'
        })
        .when('/recruiters-information', {
            templateUrl: 'html/recruiters-information/index.html',
            controller: 'VacancyController'
        })
        .when('/profiles', {
            templateUrl: 'html/profiles/index.html',
            controller: 'VacancyController'
        })
        .when('/recruiters-interview', {
            templateUrl: 'html/recruiter-profile/index.html',
            controller: 'VacancyController'
        })
        .when('/recruiters-vacancies', {
            templateUrl: 'html/recruiter-profile/vacancies.html',
            controller: 'VacancyController'
        })
        .when('/add-vacancy', {
            templateUrl: 'html/employer/add-vacancy.html',
            controller: 'VacancyController'
        })
        .when('/schedule', {
            templateUrl: 'html/schedule/schedule.html',
            controller: 'VacancyController'
        })

    .otherwise({
        redirectTo: '/home'
    });
});
