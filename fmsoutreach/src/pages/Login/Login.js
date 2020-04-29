import React from "react";
import jwt_decode from 'jwt-decode'
import {
  Form,
  Col,
  InputGroup,
  Button,
  Row,
  Container,  
  Alert
} from "react-bootstrap";

//Import CSS
import "./Login.css";

// //Import Images
import HiddenEye from "../../img/hidden-icon.png";

//API SERVICES
import * as Services from "../../_config/api";
import { envConfig } from "../../_config/config";

class Login extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      formSubmit: false,
      hidden: true,
      email: "",
      password: "",
      errors: {
        email: "Please enter email address",
        password: "Please enter password"
      }
    };
    // checkAuth(props, "front");
  }

  handleOnChange = event => {
    const { errors } = this.state;
    const { name, value } = event.target;
    let emailPattern = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    this.setState(
      {
        [name]: value
      },
      _ => {
        if (value !== "") {
          if (name === "email") {
            if (!emailPattern.test(value)) {
              errors[name] = "Please enter valid email address";
              this.setState({
                errors
              });
            } else {
              errors[name] = "";
              this.setState({
                errors
              });
            }
          } else {
            errors[name] = "";
            this.setState({
              errors
            });
          }
        } else if (value === "" && name === "email") {
          errors[name] = "Please enter email address";
          this.setState({
            errors
          });
        }
      }
    );
  };

  handleOnSubmit = event => {
    event.preventDefault();
    const { errors } = this.state;
    let isValid = true;
    this.setState(
      {
        formSubmit: true
      },
      _ => {
        Object.keys(errors).forEach(function(key) {
          if (errors[key] !== "" && isValid === true) {
            isValid = false;
          }
        });
        if (isValid === true) {
          this.handleSignInForm();
        }
      }
    );
  };

  handleSignInForm = () => {
    const { email, password } = this.state;
    let url = envConfig.AUTH_API + Services.SIGN_IN;

    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append('Access-Control-Allow-Origin', '*');
    myHeaders.append('Access-Control-Allow-Credentials', 'true');
    
    let body = {
      "email": email,
      "password": password
    }    
    let requestOptions = {
      method: "POST",
      headers: myHeaders,
      body: JSON.stringify(body)
    };
console.log("login url", url)
    fetch(url, requestOptions)
      .then(response => response.json())
      .then(result => {
        console.log("login result ", result);
        if (result.token !== undefined) {
          const decoded = jwt_decode(result.token);
          console.log("decoded ", decoded)
          localStorage.setItem("userToken", result.token);
          localStorage.setItem("user", decoded.sub);
          localStorage.setItem("role", decoded.role[0])                  
          this.props.history.push("/home");
          this.setState({
            email: "",
            password: ""
          });
        }
      })
      .catch(error => {
        console.log("error", error);
        return <Alert variant="danger">{error}</Alert>;
      });
  };

  toggleShow = () => {
    this.setState({ hidden: !this.state.hidden });
  };

  render() {
    const { email, password, errors, formSubmit } = this.state;
    return (
      <div className="login-container">
        <Row>         
          <Col md={12} className="right-side-container">
            <Container className="right-container">
              <p className="signIn-text">Sign In</p>
              <p className="signIn-sub-text">to access FMSOutReach</p>
              <Form className="signIn-form" onSubmit={this.handleOnSubmit}>
                <Form.Group controlId="formBasicEmail">
                  <Form.Control
                    type="text"
                    className="email"
                    placeholder="Email Address"
                    name="email"
                    value={email}
                    onChange={e => this.handleOnChange(e)}
                  />
                  {errors.email !== "" && formSubmit === true && (
                    <Form.Text className="error">{errors.email}</Form.Text>
                  )}
                </Form.Group>

                <Form.Group controlId="formBasicPassword">
                  <InputGroup>
                    <Form.Control
                      type={this.state.hidden ? "password" : "text"}
                      className="password"
                      placeholder="Password"
                      name="password"
                      value={password}
                      onChange={e => this.handleOnChange(e)}
                    />
                    <InputGroup.Append>
                      <img
                        className="hidden-icon"
                        src={HiddenEye}
                        alt="eye"
                        onClick={this.toggleShow}
                      />
                    </InputGroup.Append>
                  </InputGroup>
                  {errors.password !== "" && formSubmit === true && (
                    <Form.Text className="error">{errors.password}</Form.Text>
                  )}
                </Form.Group>

                <Button
                  variant="success"
                  type="submit"
                  className="signIn-button"
                >
                  Sign In
                </Button>
              </Form>
            </Container>
          </Col>
        </Row>
      </div>
    );
  }
}
export default Login;
