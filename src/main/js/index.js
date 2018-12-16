import React from 'react';
import ReactDOM from 'react-dom';
import MainPage from './templates/MainPage';
import {HashRouter } from 'react-router-dom';

window.React = React

render(
  <HashRouter>
    <div>
	  <MainPage />
    </div>
  </HashRouter>,
  document.getElementById('root')
)