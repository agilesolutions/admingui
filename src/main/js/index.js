import React from 'react';
import ReactDOM from 'react-dom';
import ClippedDrawer from './components/ClippedDrawer';
import registerServiceWorker from './registerServiceWorker';
import {HashRouter } from 'react-router-dom';

ReactDOM.render(
		<div>
			<HashRouter>
			  <PageTemplate />
			</HashRouter>
		</div>	
		, document.getElementById('root'));
registerServiceWorker();