import React from 'react';
import { Route } from 'react-router-dom';
//Import Layouts
import LoginLayout from "../_layouts/loginLayout/LoginLayout";
import DashboardLayout from "../_layouts/dashboardLayout/DashboardLayout";
import AddQuestionLayout from "../_layouts/addQuestionLayout/AddQuestionLayout";

//Login Layout
export const LoginLayoutRoute = ({ component: Component, ...rest }) => {
    return (
        <Route {...rest} render={matchProps => (
            <LoginLayout>
                <Component {...matchProps} />
            </LoginLayout>
        )} />
    )
};

//Dashboard Layout
export const DashboardLayoutRoute = ({ component: Component, ...rest }) => {
    return (
        <Route {...rest} render={matchProps => (
            <DashboardLayout>
                <Component {...matchProps} />
            </DashboardLayout>
        )} />
    )
};

//AddQuestion Layout
export const AddQuestionLayoutRoute = ({ component: Component, ...rest }) => {
    return (
        <Route {...rest} render={matchProps => (
            <AddQuestionLayout>
                <Component {...matchProps} />
            </AddQuestionLayout>
        )} />
    )
};