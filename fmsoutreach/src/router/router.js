import React from "react";
import { Router, Switch } from "react-router-dom";
import { history } from "../_helpers";
import {
  LoginLayoutRoute,
  DashboardLayoutRoute,
  AddQuestionLayoutRoute
} from "./routeLayout"; //Import Login Page Components

import Login from "../pages/Login/Login";
import Home from "../pages/Home/Home";
import Events from "../pages/Events/Events";
import QuestionList from "../pages/Configuration/QuestionList/QuestionList";
import AddQuestion from "../pages/Configuration/AddQuestion/AddQuestion";

class RouterComponent extends React.Component {
  constructor(props) {
    super(props);
    history.listen((location, action) => {
      console.log(location, action);
    });
  }
  render() {
    console.log("Router History", history);
    return (
      <Router history={history}>
        <Switch>
          {/* Login Layout */}
          <LoginLayoutRoute exact path="/" component={Login} />   
          {/* Dashboard Layout */}      
          <DashboardLayoutRoute exact path="/home" component={Home} />   
           {/* Dashboard Layout */}      
           <DashboardLayoutRoute exact path="/events" component={Events} />   
            {/* Dashboard Layout */}      
            <DashboardLayoutRoute exact path="/questions" component={QuestionList} />           
            <DashboardLayoutRoute exact path="/addquestion" component={AddQuestion} />           
            <DashboardLayoutRoute exact path="/question/:qId" component={AddQuestion} />           
        </Switch>
      </Router>
    );
  }
}
export default RouterComponent;
