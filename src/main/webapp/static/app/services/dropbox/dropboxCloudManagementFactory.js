/**
 * Created by Krzysztof Kicinger on 2015-04-27.
 */
(function() {
    var dropboxCloudManagementFactory = function ($http, dropboxSession) {

        var factory = {};

        factory.listFiles = function (directory) {
            console.log('Dropbox Cloud Factory - listFiles request for sessionId: ' + dropboxSession.sessionId);
            $http.defaults.headers.common['Content-Type'] = 'application/json';
            $http.defaults.headers.common['cloudSessionId'] = dropboxSession.sessionId;
            return $http.post('/dropbox/listFiles', directory);
        };

        return factory;
    };

    angular.module('cloudSyncApp').factory('dropboxCloudManagementFactory', dropboxCloudManagementFactory);
}());