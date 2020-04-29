import React, { Component } from 'react';
import './LoginLayout.css';

/**
 * Page Layout For Login Page
 */
class LoginLayout extends Component {
    render() {
        return (
            <div className="loginBackground">
                {this.props.children}
            </div>
        )
    }
}
export default LoginLayout;