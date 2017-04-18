import angular from 'angular';
import Home from './home/home';
import About from './about/about';
import Accounts from './accounts/accounts';

let componentModule = angular.module('app.components', [
  Home,
  About,
  Accounts
])

.name;

export default componentModule;