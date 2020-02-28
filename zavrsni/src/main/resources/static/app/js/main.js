var wafepaApp = angular.module("wafepaApp",["ngRoute"]);




wafepaApp.controller("ZadaciCtrl", function($scope, $http, $location){
	
	var url = "/api/zadaci";
	var urlStanje = "/api/stanja";
	var urlSprint = "/api/sprintovi";
	
	$scope.zadaci = [];
	$scope.stanja = [];
	$scope.sprintovi = [];
	
	$scope.nZadatak = {};
	$scope.nZadatak.ime = "";
	$scope.nZadatak.zaduzeni = "";
	$scope.nZadatak.bodovi = "";
	$scope.nZadatak.stanjeId = "";
	$scope.nZadatak.sprintId = "";
	
	$scope.sParams = {};
	$scope.sParams.ime = "";
	$scope.sParams.sprintId = "";
	
	
	$scope.pageNum = 0;
	$scope.totalPages = 1;
	
	$scope.prikaz=false;
	
	
	var getZadatke = function(){
		
		var config = {params:{}};
		
		if($scope.sParams.ime != ""){
			config.params.ime = $scope.sParams.ime;
		}
		
		if($scope.sParams.sprintId != ""){
			config.params.sprintId = $scope.sParams.sprintId;
		}
		
		
		
		config.params.pageNum = $scope.pageNum;
		
		var promise = $http.get(url, config);
		promise.then(
			function success(res){
				$scope.totalPages = res.headers("totalPages");
				$scope.zadaci = res.data;
			},
			function error(){
				alert("Nije moguce dobaviti zadatke");
			}
		);
	}
	
	getZadatke();
	
	
	var getStanja = function(){
		var promise = $http.get(urlStanje);
		promise.then(
			function success(res){
				$scope.stanja = res.data;
			},
			function error(res){
				alert("Nije moguce dobaviti stanja");
			}
		);
	}
	
	getStanja();
	
	
	
	var getSprintove = function(){
		var promise = $http.get(urlSprint);
		promise.then(
			function success(res){
				$scope.sprintovi = res.data;
			},
			function error(res){
				alert("Nije moguce dobaviti sprintove");
			}
		);
	}
	
	getSprintove();
	
	
	
	
	$scope.doAdd = function(){
		
		$http.post(url, $scope.nZadatak).then(
			function success(res){
				getZadatke();
	
			},
			function error(){
				alert("Nije moguce sacuvati zadatak");
			}
		);
	}
	
	$scope.goToEdit = function(id){
		$location.path("/zadaci/edit/" + id);
	}
	
	$scope.doDelete = function(id) {
		$http.delete(url+"/"+id).then(
		function success() {
			getZadatke();
			
		}, function error() {
			alert("Nije moguce izbrisati zadatak");
		}		
		);
	}
	
	$scope.doSearch = function(){
		$scope.pageNum = 0;
		getZadatke();
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum += direction;
		getZadatke();
	}
	
	$scope.prelaz= function(id) {
		
		var promise= $http.put(url+"/"+id+"/prelaz");
		promise.then(
		function success() {
			getZadatke();
			
		}, function error() {
			alert("Doslo je do greske");
		}		
		);
	}
});


wafepaApp.controller("EditZadatakCtrl", function($scope, $http, $routeParams, $location){
	
	var zUrl = "/api/zadaci/" + $routeParams.id;
	var stUrl = "/api/stanja";
	var spUrl = "/api/sprintovi";
	
	$scope.zadatak = {};
	$scope.zadatak.ime = "";
	$scope.zadatak.zaduzeni = "";
	$scope.zadatak.bodovi = "";
	$scope.zadatak.stanjeId = "";
	$scope.zadatak.sprintId = "";
	
	
	$scope.stanja = [];
	$scope.sprintovi = [];
	
	
	
	var getStanja = function(){
		$http.get(stUrl).then(
			function success(res){
				$scope.stanja = res.data;
				//getSprintove();
				
			},
			function error(){
				alert("Couldn't fetch stanja");
			}
		);
	}
	
	getStanja();
	
	var getSprintove = function(){
		 $http.get(spUrl).then(
			function success(res){
				$scope.sprintovi = res.data;
				//getZadatak();
			},
			function error(){
				alert("Couldn't fetch sprintove.");
			}
		);
	}
	
	getSprintove();
	
	var getZadatak = function(){
		$http.get(zUrl).then(
			function success(res){
				$scope.zadatak = res.data;
				
			},
			function error(){
				alert("Couldn't fetch zadatak.");
			}
		);
	}
	

	//getStanja();
	
	getZadatak();
	
	
	$scope.doEdit = function(){
		$http.put(zUrl, $scope.zadatak).then(
			function success(res){
				$location.path("/zadaci");
			},
			function error(res){
				alert("Something went wrong.");
			}
		);
	}
});




wafepaApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/home.html',
			controller: "HomeCtrl"
		})
		.when('/zadaci', {
			templateUrl : '/app/html/zadaci.html'
		})
		.when('/zadaci/edit/:id', {
			templateUrl : '/app/html/edit-zadatak.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);