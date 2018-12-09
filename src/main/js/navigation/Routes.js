import React from 'react'
import { BrowserRouter as Router, Route } from 'react-router-dom';
import Profilelist from '../components/Profilelist';
import addprofile from '../components/addprofile';

const Routes = () => (
  		 <Router>
	      <div>
	        <Route exact path="/" component={Profilelist} />
			<Route path='/Profilelist' component={Profilelist} />
			<Route path='/addprofile' component={addprofile} />
	      </div>
	    </Router>
)

export default Routes
