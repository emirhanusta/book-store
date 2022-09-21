import React from "react";
import {signUp} from '../api/apiCalls';
 
class UserSignupPage extends React.Component{

    state={
        userName:null,
        displayName: null,
        password:null,
        passwordRepeat:null,
        pendingApiCall: false
    };

    onChange = event => {
        const {name,value} = event.target;
        this.setState(
            {
                [name] : value
            }
        );
    };

    onClickSigUp = async event => {
        event.preventDefault();

        const {userName,displayName,password}=this.state;
        const body={
            userName,
            displayName,
            password
        }

        this.setState({pendingApiCall: true});

        try {
            const response=await signUp(body);
        } catch (error) {
            
        }
        
        this.setState({pendingApiCall: false});
       
    }

    render(){
        const {pendingApiCall}=this.state;
        return (
            <div className="container">
                <form>
                    <h1 className="text-center">Sign Up</h1>
                    <div className="form-group">
                        <label>Username</label>
                        <input className="form-control" name="userName" onChange={this.onChange}/>
                    </div>
                    <div className="form-group">
                        <label>Display Name</label>
                        <input className="form-control" name="displayName" onChange={this.onChange}/>
                    </div>
                    <div className="form-group">
                        <label>Password</label>
                        <input className="form-control" type="password" name="password" onChange={this.onChange}/>
                    </div>
                    <div className="form-group">
                        <label>Password Repeat</label>
                        <input className="form-control" type="password" name="passwordRepeat" onChange={this.onChange}/>
                    </div>
                    <div className="text-center">
                        <button className="btn btn-primary" onClick={this.onClickSigUp} disabled={pendingApiCall}>{pendingApiCall && <span class="spinner-border spinner-border-sm"></span>}Sign Up</button>
                    </div>
            </form>
        </div>

        );
    }
}
export default UserSignupPage;