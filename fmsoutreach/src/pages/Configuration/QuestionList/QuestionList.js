import React from "react";
import { MDBDataTable,MDBInput } from "mdbreact";
import { Alert } from "react-bootstrap";
//API SERVICES
import * as Services from "../../../_config/api";
import { envConfig } from "../../../_config/config";
import { history } from "../../../_helpers";
class QuestionList extends React.Component {
  constructor() {
    super();
    this.state = {
      eventData: [],
      rowData: [],
      isLoading: false
    }
  }

  componentDidMount() {
    this.getAllQuestions();
  }


  /**
   * Get all Questions from MongoDB
   */
  getAllQuestions = () => {
    this.setState({ isLoading: true });
    let url = envConfig.AUTH_API + Services.GET_ALL_QUESTIONS_MDB;
    console.log('url',url);

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
        let rowData = [];
        if (result.length > 0) {
          result.forEach((data, key) => {
            key = data.id;

            rowData.push({
              id: data.id ? data.id : "-",
              question: data.question ? data.question : "-",
              feedbackType: data.feedbackType ? data.feedbackType : "-",
            questionType:data.questionType ? data.questionType : "-",
            questionAnswer:data.questionAnswer.length ,
            check: (
             
                <MDBInput  
                  type="button"
                  name={"switch_" + data.id }
                  id={"switch_" + data.id}
                  value="Edit" 
                  onClick={()=> history.push("/AddQuestion/" + data.id)}
                />
              
              ),
            });

            this.setState(
              {
                rowData: rowData,
                isLoading: false
              },
              _ => {
                this.setQuestionListData();
              }
            );
          });
        } else {
          this.setState({ isLoading: false });
          return <Alert variant="danger">{"No data found!"}</Alert>;
        }
      })
      .catch(error => {
        console.log("error", error);
        this.setState({ isLoading: false });
        return <Alert variant="danger">{error}</Alert>;
      });
  };

  addQuestion = event => {
    history.push("/addquestion")
};

  /**
   * Render and set list view data using data table
   */
  setQuestionListData = () => {
    this.setState({
      questionListData: {
        columns: [
          {
            label: "QuestionId ",
            field: "id",
            sort: "asc",
            width: 270
          },
          {
            label: "Question",
            field: "question",
            sort: "asc",
            width: 270
          },
          {
            label: "FeedbackType",
            field: "feedbackType",
            sort: "asc",
            width: 200
          },
          {
            label: "QuestionAnswer",
            field: "questionAnswer",
            sort: "asc",
            width: 80
          },

          {
            label: "Actions",
            field: "check"
          }
        ],
        rows: this.state.rowData
      }
    });
  }
  render() {
    const { questionListData, isLoading } = this.state;
    return (
      <div className="lead-wraper">

        {/* end of Secondary Nav */}
        <div className="filter-wrap">
          <div className="row justify-content-between">
            <div className="col-4">

              <h2 className="crm-type-title">QuestionList</h2>
              <button onClick={this.addQuestion}>
                Add Question
              </button>
            </div>
          </div>
        </div>{" "}
        {/* end of Filter Wrap */}
        <div className="crm-data-wrap">
          <div className="list-view-wrap">
            {isLoading == false && <MDBDataTable data={questionListData} />}
            {isLoading == true && (
              <div className="loading">
                <p>Loading...</p>
              </div>
            )}
          </div>

        </div>
      </div>
    );
  }

}

export default QuestionList;