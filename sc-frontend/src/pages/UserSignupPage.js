import React from "react";
import {signUp} from '../api/apiCalls';
import Input from "../components/Input"; 

class UserSignupPage extends React.Component{

    state={
        userName:null,
        displayName: null,
        password:null,
        passwordRepeat:null,
        pendingApiCall: false,
        errors:{}
    };

    onChange = event => {
        const {name,value} = event.target;
        const errors= {...this.state.errors};
        errors[name]=undefined;
        
        this.setState(
            {
                [name] : value,errors
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
            if(error.response.data.validationError){
                this.setState({errors: error.response.data.validationError});
          }
        }
        
        this.setState({pendingApiCall: false});
       
    }

    render(){
        const {pendingApiCall,errors}=this.state;
        const {userName, displayName,password,passwordRepeat}= errors;
        return (
            <div className="container">
                <form>
                    <h1 className="text-center">Sign Up</h1>
                    <Input name="userName" label="Username" error={userName} onChange={this.onChange}/>

                    <Input name="displayName" label="Display Name" error={displayName} onChange={this.onChange}/>

                    <Input name="password" label="Password" error={password} type="password" onChange={this.onChange}/>
 
                    <Input name="passwordRepeat" label="Password Repeat" error={passwordRepeat} type="password" onChange={this.onChange}/>
  
                    <div className="text-center">
                        <button className="btn btn-primary" onClick={this.onClickSigUp} disabled={pendingApiCall}>{pendingApiCall && <span className="spinner-border spinner-border-sm"></span>}Sign Up</button>
                    </div>
            </form>
        </div>

        );
    }
}
export default UserSignupPage;