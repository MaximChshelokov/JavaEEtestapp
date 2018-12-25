var app = angular.module('NewsManager', []);

app.controller('NewsController', ['$scope', 'NewsService', function ($scope, NewsService) {

    $scope.updateNews = function () {
        NewsService.updateNews($scope.news.id, $scope.news.title, $scope.news.date, $scope.news.brief,
                               $scope.news.content)
            .then(function success() {
                      $scope.message = 'User data updated!';
                      $scope.errorMessage = '';
                      $scope.showViewNews();
                  },
                  function error(response) {
                      $scope.errors = response.data;
                      $scope.errorMessage = 'Error updating news!';
                      $scope.message = '';
                  });
    };

    $scope.getNews = function (newsId) {
        var id = newsId;
        NewsService.getNews(id)
            .then(function success(response) {
                      $scope.news = response.data[0];
                      $scope.news.id = id;
                      $scope.news.date = new Date($scope.news.date);
                      $scope.message = '';
                      $scope.errorMessage = '';
                  },
                  function error(response) {
                      $scope.message = '';
                      if (response.status === 404) {
                          $scope.errorMessage = 'News not found!';
                      }
                      else {
                          $scope.errorMessage = "Error getting news!";
                      }
                  });
    };

    $scope.addNews = function () {
        if ($scope.news != null && $scope.news.title) {
            NewsService.addNews($scope.news.title, $scope.news.date, $scope.news.brief, $scope.news.content)
                .then(function success(response) {
                          $scope.message = 'News added!';
                          $scope.errorMessage = '';
                          $scope.news = response.data;
                          $scope.news.date = new Date($scope.news.date);
                          $scope.showViewNews();
                      },
                      function error(response) {
                          $scope.errors = response.data;
                          $scope.errorMessage = 'Error adding news!';
                          $scope.message = '';
                      });
        }
        else {
            $scope.errorMessage = 'Please enter a title!';
            $scope.message = '';
        }
    };

    $scope.deleteNews = function (id) {
        NewsService.deleteNews(id)
            .then(function success() {
                      $scope.message = 'News deleted!';
                      $scope.news = null;
                      $scope.errorMessage = '';
                      $scope.getAllNews();
                  },
                  function error() {
                      $scope.errorMessage = 'Error deleting news!';
                      $scope.message = '';
                  })
    };

    $scope.getAllNews = function () {
        NewsService.getAllNews()
            .then(function success(response) {
                      $scope.news_list = response.data;
                      $scope.news_list.forEach(function (element) {
                          element.date = new Date(element.date);
                      });
                      $scope.message = '';
                      $scope.errorMessage = '';
                      $scope.showAllNews();
                  },
                  function error() {
                      $scope.message = '';
                      $scope.errorMessage = 'Error getting list of news!';
                  });
    };

    $scope.changeLang = function (locale) {
        NewsService.getLocaleMessages(locale)
            .then(function success(response) {
                $scope.messages = response.data;
                if (locale === 'en') {
                    $scope.ruClass = '';
                    $scope.enClass = 'selected';
                } else {
                    $scope.ruClass = 'selected';
                    $scope.enClass = '';
                }
            })

    };

    $scope.editNews = function (id) {
        $scope.getNews(id);
        $scope.showAddNewsForm();
    };

    $scope.viewNews = function (id) {
        $scope.getNews(id);
        $scope.showViewNews();
    };

    $scope.createNews = function () {
        $scope.news = {id: 0, title: '', date: new Date, brief: '', content: ''};
        $scope.showAddNewsForm();
    };

    $scope.showAddNewsForm = function () {
        $scope.feed_visible = false;
        $scope.form_visible = true;
        $scope.view_visible = false;
        $scope.view_class = '';
        $scope.form_class = 'selected-item';
        $scope.currentTitle ='application.edit-news.title';
    };

    $scope.showAllNews = function () {
        $scope.feed_visible = true;
        $scope.form_visible = false;
        $scope.view_visible = false;
        $scope.view_class = 'selected-item';
        $scope.form_class = '';
        $scope.currentTitle = 'application.news-list.title';
    };

    $scope.showViewNews = function () {
        $scope.feed_visible = false;
        $scope.form_visible = false;
        $scope.view_visible = true;
        $scope.view_class = '';
        $scope.form_class = '';
        $scope.currentTitle = 'application.add-news.title';
    };

    $scope.changeLang('en');
    $scope.getAllNews();

}]);

app.service('NewsService', ['$http', function ($http) {

    this.getNews = function getNews(NewsId) {
        return $http({
                         method: 'GET',
                         url: 'rest/news/' + NewsId
                     });
    };

    this.addNews = function addNews(title, date, brief, content) {
        return $http({
                         method: 'POST',
                         url: 'rest/news',
                         data: {title: title, date: date, brief: brief, content: content}
                     });
    };

    this.deleteNews = function deleteNews(id) {
        return $http({
                         method: 'DELETE',
                         url: 'rest/news/' + id
                     })
    };

    this.updateNews = function updateUser(id, title, date, brief, content) {
        return $http({
                         method: 'PUT',
                         url: 'rest/news/' + id,
                         data: {title: title, date: date, brief: brief, content: content}
                     })
    };

    this.getAllNews = function getAllNews() {
        return $http({
                         method: 'GET',
                         url: 'rest/news'
                     });
    };

    this.getLocaleMessages = function getLocaleMessages(locale) {
        return $http({
                         method: 'GET',
                         url: 'rest/messages?lang=' + locale
                     });
    }

}]);

app.directive('ngConfirmClick', [
    function () {
        return {
            link: function (scope, element, attr) {
                var msg = attr.ngConfirmClick || "Are you sure?";
                var clickAction = attr.confirmedClick;
                element.bind('click', function () {
                    if (window.confirm(msg)) {
                        scope.$eval(clickAction)
                    }
                });
            }
        };
    }]);