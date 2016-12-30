/**
 * IndiaCP API
 * This API will drive the UI
 *
 * OpenAPI spec version: 1.0.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// /// <reference path="api.d.ts" />
/* tslint:disable:no-unused-variable member-ordering */
var app;
(function (app) {
    var services;
    (function (services) {
        "use strict";
        var IssuerService = (function () {
            function IssuerService($http, $httpParamSerializer) {
                this.$http = $http;
                this.$httpParamSerializer = $httpParamSerializer;
                this.basePath = "http://52.172.42.128:8080/indiacp/indiacpprogram";
                this.defaultHeaders = {};
            }
            IssuerService.prototype.extendObj = function (objA, objB) {
                for (var key in objB) {
                    if (objB.hasOwnProperty(key)) {
                        objA[key.toString()] = objB[key.toString()];
                    }
                }
                return objA;
            };
            /**
             * Get All Open CP Issues for the given Issuer/Investor. Open CP Issues refers to the Issues that are yet to mature
             * This returns all the CP Issues under the umbrella CP Program identified by an Id provided by the call
             * @param entity issuer or investor id that uniquely maps to the DL node
             */
            IssuerService.prototype.fetchAllCP = function (entity, extraHttpRequestParams) {
                var localVarPath = this.basePath + "/cpissues/open/{entity}"
                    .replace("{" + "entity" + "}", String(entity));
                var queryParameters = {};
                var headerParams = this.extendObj({}, this.defaultHeaders);
                // verify required parameter "entity" is not null or undefined
                if (entity === null || entity === undefined) {
                    throw new Error("Required parameter entity was null or undefined when calling fetchAllCP.");
                }
                var httpRequestParams = {
                    method: "GET",
                    url: localVarPath,
                    json: true,
                    params: queryParameters,
                    headers: headerParams
                };
                if (extraHttpRequestParams) {
                    httpRequestParams = this.extendObj(httpRequestParams, extraHttpRequestParams);
                }
                return this.$http(httpRequestParams);
            };
            /**
             * Get CP Issues allotted under a given CP Program
             * This returns all the CP Issues under the umbrella CP Program identified by an Id provided by the call
             * @param issuer issuer id that uniquely maps to the issuer DL node
             * @param cpProgramId CP Program ID that uniquely identifies the CP Program issued by the Issuer
             */
            IssuerService.prototype.fetchAllCPForCPProgram = function (issuer, cpProgramId, extraHttpRequestParams) {
                var localVarPath = this.basePath + "/cpissues/{issuer}/{cpProgramId}"
                    .replace("{" + "issuer" + "}", String(issuer))
                    .replace("{" + "cpProgramId" + "}", String(cpProgramId));
                var queryParameters = {};
                var headerParams = this.extendObj({}, this.defaultHeaders);
                // verify required parameter "issuer" is not null or undefined
                if (issuer === null || issuer === undefined) {
                    throw new Error("Required parameter issuer was null or undefined when calling fetchAllCPForCPProgram.");
                }
                // verify required parameter "cpProgramId" is not null or undefined
                if (cpProgramId === null || cpProgramId === undefined) {
                    throw new Error("Required parameter cpProgramId was null or undefined when calling fetchAllCPForCPProgram.");
                }
                var httpRequestParams = {
                    method: "GET",
                    url: localVarPath,
                    json: true,
                    params: queryParameters,
                    headers: headerParams
                };
                if (extraHttpRequestParams) {
                    httpRequestParams = this.extendObj(httpRequestParams, extraHttpRequestParams);
                }
                return this.$http(httpRequestParams);
            };
            /**
             * Fetch All CPPrograms for the current Issuer
             * Returns all the CP Programs for the current issuer
             * @param issuer issuer id that uniquely maps to the issuer DL node
             */
            IssuerService.prototype.fetchAllCPProgram = function (issuer, extraHttpRequestParams) {
                var localVarPath = this.basePath + "/cpprograms/{issuer}"
                    .replace("{" + "issuer" + "}", String(issuer));
                var queryParameters = {};
                var headerParams = this.extendObj({}, this.defaultHeaders);
                // verify required parameter "issuer" is not null or undefined
                if (issuer === null || issuer === undefined) {
                    throw new Error("Required parameter issuer was null or undefined when calling fetchAllCPProgram.");
                }
                var httpRequestParams = {
                    method: "GET",
                    url: localVarPath,
                    json: true,
                    params: queryParameters,
                    headers: headerParams
                };
                if (extraHttpRequestParams) {
                    httpRequestParams = this.extendObj(httpRequestParams, extraHttpRequestParams);
                }
                return this.$http(httpRequestParams);
            };
            /**
             * Get All Open CP Issues for the given Issuer/Investor. Open CP Issues refers to the Issues that are yet to mature
             * This returns all the CP Issues under the umbrella CP Program identified by an Id provided by the call
             * @param entity issuer or investor id that uniquely maps to the DL node
             * @param cpIssueId Unique identifier of the CP Issue to be fetched
             */
            IssuerService.prototype.fetchCP = function (entity, cpIssueId, extraHttpRequestParams) {
                var localVarPath = this.basePath + "/cpissue/{entity}/{cpIssueId}"
                    .replace("{" + "entity" + "}", String(entity))
                    .replace("{" + "cpIssueId" + "}", String(cpIssueId));
                var queryParameters = {};
                var headerParams = this.extendObj({}, this.defaultHeaders);
                // verify required parameter "entity" is not null or undefined
                if (entity === null || entity === undefined) {
                    throw new Error("Required parameter entity was null or undefined when calling fetchCP.");
                }
                // verify required parameter "cpIssueId" is not null or undefined
                if (cpIssueId === null || cpIssueId === undefined) {
                    throw new Error("Required parameter cpIssueId was null or undefined when calling fetchCP.");
                }
                var httpRequestParams = {
                    method: "GET",
                    url: localVarPath,
                    json: true,
                    params: queryParameters,
                    headers: headerParams
                };
                if (extraHttpRequestParams) {
                    httpRequestParams = this.extendObj(httpRequestParams, extraHttpRequestParams);
                }
                return this.$http(httpRequestParams);
            };
            /**
             * Get CP Program details by Id
             * This returns a single CP Program identified by an Id provided by the call
             * @param issuer issuer id that uniquely maps to the issuer DL node
             * @param cpProgramId CP Program ID that uniquely identifies the CP Program issued by the Issuer
             */
            IssuerService.prototype.fetchCPProgram = function (issuer, cpProgramId, extraHttpRequestParams) {
                var localVarPath = this.basePath + "/cpprogram/{issuer}/{cpProgramId}"
                    .replace("{" + "issuer" + "}", String(issuer))
                    .replace("{" + "cpProgramId" + "}", String(cpProgramId));
                var queryParameters = {};
                var headerParams = this.extendObj({}, this.defaultHeaders);
                // verify required parameter "issuer" is not null or undefined
                if (issuer === null || issuer === undefined) {
                    throw new Error("Required parameter issuer was null or undefined when calling fetchCPProgram.");
                }
                // verify required parameter "cpProgramId" is not null or undefined
                if (cpProgramId === null || cpProgramId === undefined) {
                    throw new Error("Required parameter cpProgramId was null or undefined when calling fetchCPProgram.");
                }
                var httpRequestParams = {
                    method: "GET",
                    url: localVarPath,
                    json: true,
                    params: queryParameters,
                    headers: headerParams
                };
                if (extraHttpRequestParams) {
                    httpRequestParams = this.extendObj(httpRequestParams, extraHttpRequestParams);
                }
                return this.$http(httpRequestParams);
            };
            /**
             * Issue new CP Program
             * This creates a new CP Program with the details provided
             * @param cpprogramDetails Details of the CP Program to be Issued
             * @param issuer Issuer id that uniquely maps to the issuer DL node
             */
            IssuerService.prototype.issueCPProgram = function (cpprogramDetails, issuer, extraHttpRequestParams) {
                var localVarPath = this.basePath + "/cpprogram/{issuer}"
                    .replace("{" + "issuer" + "}", String(issuer));
                var queryParameters = {};
                var headerParams = this.extendObj({}, this.defaultHeaders);
                // verify required parameter "cpprogramDetails" is not null or undefined
                if (cpprogramDetails === null || cpprogramDetails === undefined) {
                    throw new Error("Required parameter cpprogramDetails was null or undefined when calling issueCPProgram.");
                }
                // verify required parameter "issuer" is not null or undefined
                if (issuer === null || issuer === undefined) {
                    throw new Error("Required parameter issuer was null or undefined when calling issueCPProgram.");
                }
                var httpRequestParams = {
                    method: "POST",
                    url: localVarPath,
                    json: true,
                    data: cpprogramDetails,
                    params: queryParameters,
                    headers: headerParams
                };
                if (extraHttpRequestParams) {
                    httpRequestParams = this.extendObj(httpRequestParams, extraHttpRequestParams);
                }
                return this.$http(httpRequestParams);
            };
            return IssuerService;
        }());
        IssuerService.$inject = ["$http", "$httpParamSerializer"];
        angular
            .module("app.services")
            .service("app.services.IssuerService", IssuerService);
    })(services = app.services || (app.services = {}));
})(app || (app = {}));
//# sourceMappingURL=issuer.service.js.map