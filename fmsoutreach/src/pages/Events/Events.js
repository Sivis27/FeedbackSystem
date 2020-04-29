import React from "react";
import { MDBDataTable } from "mdbreact";
import { Alert } from "react-bootstrap";

//API SERVICES
import * as Services from "../../_config/api";
import { envConfig } from "../../_config/config";

import { common } from "../../_helpers/common";

class Events extends React.Component {
  constructor() {
    super();
    this.state = {
      eventData: [],
      rowData: [],
      isLoading: false
    }
  }

  componentDidMount() {
    this.getAllEvents();
  }


  /**
   * Get all Events from DB
   */
  getAllEvents = () => {
    this.setState({ isLoading: true });
    let url = envConfig.AUTH_API + Services.GET_ALL_EVENTS + "/poc";
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
            key = data.event_id;

            rowData.push({
              event_id: data.event_id ? data.event_id : "-",
              eventname: data.eventname ? data.eventname : "-",
              event_date: data.event_date ? common.formatDate(data.event_date, "DD MMMM") : "-",
              poc_name: data.poc_name ? data.poc_name : "-"
            });

            this.setState(
              {
                rowData: rowData,
                isLoading: false
              },
              _ => {
                this.setDealData();
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


  /**
   * Render and set list view data using data table
   */
  setDealData = () => {
    this.setState({
      eventData: {
        columns: [
          {
            label: "event_id",
            field: "event_id",
            sort: "asc",
            width: 270
          },
          {
            label: "event name",
            field: "eventname",
            sort: "asc",
            width: 270
          },
          {
            label: "event_date",
            field: "event_date",
            sort: "asc",
            width: 200
          },
          {
            label: "poc_name",
            field: "poc_name",
            sort: "asc",
            width: 100
          }

        ],
        rows: this.state.rowData
      }
    });
  }
  render() {
    const { eventData, isLoading } = this.state;
    return (
      <div className="lead-wraper">

        {/* end of Secondary Nav */}
        <div className="filter-wrap">
          <div className="row justify-content-between">
            <div className="col-4">

              <h2 className="crm-type-title">Events</h2>

            </div>
          </div>
        </div>{" "}
        {/* end of Filter Wrap */}
        <div className="crm-data-wrap">
          <div className="list-view-wrap">
            {isLoading == false && <MDBDataTable data={eventData} />}
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

export default Events;