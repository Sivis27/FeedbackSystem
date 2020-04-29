import React, { Component } from 'react';
import './AddQuestionLayout.css'
// Import Component
import Header from "../../_components/_header/Header";
import Footer from "../../_components/_footer/Footer";
/**
 * Page Layout for AddQuestion
 */

class AddQuestionLayout extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        return (
            <div className="page">
                <div className="page-layout">
                    <Header></Header>
                </div>
                <div className="container-wrapper">
                    {this.props.children}
                </div>
                <Footer></Footer>
            </div>
        )
    }
}

export default AddQuestionLayout;