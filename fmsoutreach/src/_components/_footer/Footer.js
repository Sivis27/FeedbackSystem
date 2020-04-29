import React from "react";

//Import CSS
import "./Footer.css";


class Footer extends React.Component {
    constructor(props) {
        super(props);
        this.state = {};
    }
    render() {
        return (
            <footer className="page-footer font-small blue">

                <div className="footer-copyright text-center py-3">
                    © 2020 OutReach Feedback Management System - Cognizant All Rights Reserved
                </div>

            </footer>
        )
    }
}
export default Footer;