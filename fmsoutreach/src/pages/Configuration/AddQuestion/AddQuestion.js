import React from "react";
import {
  Form,
  Col,
  InputGroup,
  Button,
  Row,
  Container,
  Alert
} from "react-bootstrap";

import './AddQuestion.css'

//API SERVICES
import * as Services from "../../../_config/api";
import { envConfig } from "../../../_config/config";


class AddQuestion extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      formSubmit: false,
      hidden: true,
      participated: "",
      notParticipated: "",
      unregistered: "",
      mulitpleAnswers: "",
      freeTextAnswers: "",
      customQuestions: "",
      question: "",
      questionId: "",
      answer: "",
      isLoading: false,
      errors: {
        question: "Question name should not empty"

      }
    };
    // checkAuth(props, "front");
  }

  componentDidMount() {
    if (this.props.qId != undefined) {
      this.setState({
        questionId: this.props.qId
      }, () => {
        //this.hookDidMountMethods();
      });
    }
  }


  /**
   * Get all Events from DB
   */
  getAllEvents = (questionId) => {
    this.setState({ isLoading: true });
    let url = envConfig.AUTH_API + Services.GET_ALL_EVENTS + "/" + questionId;
    console.log('url', url);

    let token = localStorage.getItem("userToken");
    console.log(token);
    let headers = {
      Accept: "application/vnd.oracle.adf.resourcecollection+json",
      Authorization: "Bearer " + token
    };

    let requestOptions = {
      method: "GET",
      headers: headers
    };

    console.log(url, requestOptions);

    fetch(url, requestOptions)
      .then(response => response.json())
      .then(result => {
      }
      );
  }

  render() {
    const { participated, notParticipated, unregistered, mulitpleAnswers,
      freeTextAnswers, customQuestions, answer1,
      answer2, answer3
    } = this.state;
    return (
      <div>
        {/* <Form onSubmit={this.handleSubmit}>
          <div className="formWhiteBg">
            <div className="formHead">
              <h2>Add Question</h2>
            </div>
            <Row>
              <Col sm={6}>
                <Form.Group>
                  <Form.Label>Feedback Type*</Form.Label>
                  <Form.Control type="text"
                    name="orderNo"
                    value={orderNo}
                    onChange={e => this.handleOnChange(e)} />
                  {errors.orderNo !== "" && formSubmit === true && (
                    <Form.Text className="error">{errors.orderNo}</Form.Text>
                  )}
                </Form.Group>
              </Col>
              <Col sm={6}>
                <Form.Group>
                  <Form.Label>Status Code*</Form.Label>
                  <Form.Control type="text"
                    name="statusCode"
                    value={statusCode}
                    onInput={e => this.toInputUppercase(e)}
                    onChange={e => this.handleOnChange(e)} />
                  {errors.statusCode !== "" && formSubmit === true && (
                    <Form.Text className="error">{errors.statusCode}</Form.Text>
                  )}
                </Form.Group>
              </Col>
              <Col sm={6}>
                <Form.Group>
                  <Form.Label>Question*</Form.Label>
                  <Form.Control type="text"
                    name="orderNo"
                    value={orderNo}
                    onChange={e => this.handleOnChange(e)} />
                  {errors.orderNo !== "" && formSubmit === true && (
                    <Form.Text className="error">{errors.orderNo}</Form.Text>
                  )}
                </Form.Group>
              </Col>
            </Row>
          </div>
        </Form> */}
      </div>
    );
  }

  //   handleOnSubmit = event => {
  //     event.preventDefault();
  //     const { errors } = this.state;
  //     let isValid = true;
  //     this.setState(
  //       {
  //         formSubmit: true
  //       },
  //       _ => {
  //         Object.keys(errors).forEach(function (key) {
  //           if (errors[key] !== "" && isValid === true) {
  //             isValid = false;
  //           }
  //         });
  //         if (isValid === true) {
  //           this.handleSignInForm();
  //         }
  //       }
  //     );
  //   };


  //   handleSignInForm = () => {
  //     const { participated, notParticipated, unregistered, mulitpleAnswers, freeTextAnswers,
  //       customQuestions, answer} = this.state;
  //     let url = envConfig.AUTH_API + Services.POST_ADDQUESTION;
  // console('add question url ', url);
  //     let myHeaders = new Headers();
  //     myHeaders.append("Content-Type", "application/json");
  //     myHeaders.append('Access-Control-Allow-Origin', '*');
  //     myHeaders.append('Access-Control-Allow-Credentials', 'true');

  //     let body = {
  //       "participated": participated,
  //       "notParticipated": notParticipated,
  //       "unregistered": unregistered,
  //       "allowmulitpleanswers": mulitpleAnswers,
  //       "freetextanswer": freeTextAnswers,
  //       "customquestion": customQuestions,
  //       "answer": answer

  //     }

  //     let requestOptions = {
  //       method: "POST",
  //       headers: myHeaders,
  //       body: JSON.stringify(body)
  //     };

  //     fetch(url, requestOptions)
  //       .then(response => response.json())
  //       .then(result => {
  //         console.log("Question result ", result);
  //         if (result.token !== undefined) {
  //           localStorage.setItem("userToken", result.token);
  //           this.props.history.push("/AddQuestion");
  //           this.setState({
  //             participated: "",
  //             notParticipated: "",
  //             unregistered: "",
  //             mulitpleAnswers: "",
  //             freeTextAnswers: "",
  //             customQuestions: "",
  //             answer: "",

  //           });
  //         }
  //       })
  //       .catch(error => {
  //         console.log("error", error);
  //         return <Alert variant="danger">{error}</Alert>;
  //       });
  //   };


}

export default AddQuestion;
