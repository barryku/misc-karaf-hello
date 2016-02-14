var app = angular.module('app', []);

app.controller('MainCtrl', ['$scope', '$http', '$location', function($scope, $http, $location) {
  $scope.orderByField = 'createdAt';
  $scope.reverseSort = false;

  var url = $location.absUrl();
  var job = url.split('job=')[1];
  if (job.indexOf('#') > 0) {
	job = job.split('#')[0];
  }
  if (job.indexOf('&') > 0) {
	job = job.split('&')[0];
  }
  //console.log('job:' + job);
  $http.get('http://localhost:8181/cxf/barry/auto-deploy/logs/' + job).then(
	function(data) {
		$scope.data = data.data;
	
	
	}
  );
  
  
}]);