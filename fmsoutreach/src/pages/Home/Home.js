import React from "react";
import './Home.css'
import { Link } from "react-router-dom";
import { Alert, Spinner } from "react-bootstrap";
//API SERVICES
import * as Services from "../../_config/api";
import { envConfig } from "../../_config/config";

class Home extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoading: false
        }
    }
    componentDidMount() {
        this.getDashboardDetail();
    }


    /**
     * Get Dashboard Details
     */
    getDashboardDetail() {
        // let val = 0
        // if (id != undefined) {
        //   val = id.replace(":", "");
        //   this.setState({ transactionID: val });
        // }

        this.setState({ isLoading: true });
        //let url = envConfig.BASE_API + Services.GET_DASHBOARD_DETAILS + "/" + val;
        let url = envConfig.AUTH_API + Services.GET_DASHBOARD_DETAILS + "/admin";
        let token = localStorage.getItem("userToken");

        let headers = new Headers();
        headers.append("Content-Type", "application/json");
        headers.append('Access-Control-Allow-Origin', '*');
        headers.append('Access-Control-Allow-Credentials', 'true');
        headers.append('Authorization', "Bearer " + token)

        // let headers = {
        //   Accept: "application/vnd.oracle.adf.resourcecollection+json",
        //   Authorization: "Bearer " + token
        // };

        let requestOptions = {
            method: "GET",
            headers: headers
        };

        fetch(url, requestOptions)
            .then(response => response.json())
            .then(result => {
                this.setState({ isLoading: false });
                console.log("dashboard result ", result)
                this.setState({
                  eventId: result.event_id,
                  livesImpacted: result.lives_impacted,
                  totVolunteers: result.total_no_of_volunteers,
                  totParticipants: result.total_no_of_Participants 
                 });
            }).catch(error => {
                console.log("error", error);
                this.setState({ isLoading: false });
                return <Alert variant="danger">{error}</Alert>;
            });
    };

    render() {
        const { isLoading } = this.state
        return (
            <div>
                
                    {isLoading == true && (
                        <Spinner animation="border" variant="primary" />
                    )}
                    {isLoading == false && (
                        <div className="d-flex flex-row">
                        <div className="p-2">
                            Total Events
                           {this.state.eventId} <Link>View Details</Link>
                        </div>
                        <div className="p-2">
                            Lives Impacted
                            {this.state.livesImpacted}  <Link>View Details</Link>
                        </div>
                        <div className="p-2">
                            Total Volunteers
                            {this.state.totVolunteers}   <Link>View Details</Link>
                        </div>
                        <div className="p-2">
                            Total Participants
                            {this.state.totParticipants}
                            <Link>View Details</Link>
                        </div>
                        </div>
                    )}                
            </div>
        );
    }
}
export default Home;