import React from 'react';
import ReactDOM from 'react-dom';
import ClippedDrawer from './components/ClippedDrawer';
import registerServiceWorker from './registerServiceWorker';
import {BrowserRouter } from 'react-router-dom';

ReactDOM.render(
		<div>
			<BrowserRouter>
			  <ClippedDrawer />
			</BrowserRouter>
		</div>	
		, document.getElementById('root'));
registerServiceWorker();