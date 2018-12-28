import React from 'react';
import { render } from 'react-dom'
import MainPage from './templates/MainPage';
import {HashRouter } from 'react-router-dom';
import Store from './data/Store';

window.React = React

render(
		
	

  <HashRouter>
    <div>
		<Store.Provider value={{id: 1, domain: 'domain', name: 'hame', host: 'host', environment: 'environment', ticket: 'ticket', template: 'template'}}>
	  			<MainPage />
    	</Store.Provider>
    </div>
  </HashRouter>,
  document.getElementById('ReactContainer')
)
