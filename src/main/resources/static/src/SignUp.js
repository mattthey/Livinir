import React,{Component} from 'react';
import { Redirect } from 'react-router';
import './Sign.css'

class SignUp extends Component{
    constructor(props){
        super(props);
        this.state = {
            redirect: false
        };
        this.sentData = this.sentData.bind(this);
    }
    sentData(e) {
        console.log(e.target.elements)
        this.setState({ redirect: true })
    }

    render() {
        if (this.state.redirect) {
            this.state.redirect = false;
            return <Redirect to='/'/>;
        }
        return(<div className='ParentSign'>
            <form onSubmit={this.sentData} className='Sign'>
                <input type='text' name='Email' placeholder='Enter your email'/>
                <input type='text' name='FirstName' placeholder='Enter your First Name'/>
                <input type='text' name='LastName' placeholder='Enter your Last Name'/>
                <input type='text' name='Password' placeholder='Enter password'/>
                <input type='date' name='DateOfBirth' placeholder='Enter your birthday'/>
                <p> Your gender:
                    <input type="radio" name="Sex" value='male'/> Male
                    <input type="radio" name="Sex" value='female'/> Female
                </p>
                <button >Sign Up</button>
            </form>
        </div>)
    }
}
export default SignUp