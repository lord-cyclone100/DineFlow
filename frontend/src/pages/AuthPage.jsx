import { useState } from "react"
import { RegisterForm } from "../components/ui/RegisterForm"
import { LoginForm } from "../components/ui/LoginForm"

export const AuthPage = () => {
	const [tab, setTab] = useState('Register')

	const handleTabClick = (e) => {
		console.log(e.target.textContent)
		setTab(e.target.textContent)
	}
	return (
		<>
			{/* <RegisterForm /> */}
			<div onClick={handleTabClick}>
				<button>Register</button>
				<button>Login</button>
			</div>
			<div>
				{tab === 'Register' ? <RegisterForm /> : <LoginForm />}
			</div>
		</>
	)
}