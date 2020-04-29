import React from "react";
import { Navbar, Nav, NavDropdown } from "react-bootstrap";

//Import CSS
import "./Header.css";
import { history } from "../../_helpers";

class Header extends React.Component {
    constructor(props) {
        super(props);
        this.state = {};
        
    }
    logout = e => {
        localStorage.removeItem("userToken");
        history.push("/");
    };

    render() {
        return (
            <>

                <Navbar bg="primary" variant="dark">
                    <Navbar.Brand href="/home">OutReach FMS</Navbar.Brand>
                    <Nav className="mr-auto">
                        <Nav.Link href="/home">Dashboards</Nav.Link>
                        <Nav.Link href="/events">Events</Nav.Link>
                        <Nav.Link href="#">Reports</Nav.Link>
                        <NavDropdown title="Configuration" id="basic-nav-dropdown">
                            <NavDropdown.Item href="#">PMO User</NavDropdown.Item>
                            <NavDropdown.Item href="/questions">Feedback Questions</NavDropdown.Item>
                        </NavDropdown>
                    </Nav>
                    <Nav className="mr-sm-2">
                        <Nav.Link href="#" onClick={e => this.logout(e)}>Logout</Nav.Link>
                    </Nav>
                </Navbar>

            </>
        )
    }
}
export default Header;