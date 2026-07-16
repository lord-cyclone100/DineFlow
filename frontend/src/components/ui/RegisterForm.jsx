import { useState } from "react"
import { api } from "../../api/axios"

export const RegisterForm = () => {

	const [formData, setFormData] = useState({
		name: "",
		email: "",
		password: "",
		phoneNumber: ""
	})

	const handleChange = (e) => {
		const {name, value} = e.target

		setFormData((prev) =>({...prev, [name]: value}))
	}

	const handleFormSubmit = async (e) => {
		e.preventDefault()
		try{
			const response = await api.post('/auth/register', formData)
			console.log(response.data)
		}
		catch(error){
			console.log(error.response.data.message)
		}
	}

  return(
    <>
      <form action="" onSubmit={handleFormSubmit}>
				<div>
					<label htmlFor="name">Name : </label>
					<input type="text" name="name" value={formData.name} onChange={handleChange} className="border"/>
				</div>
				<div>
					<label htmlFor="email">Email : </label>
					<input type="email" name="email" value={formData.email} onChange={handleChange} className="border"/>
				</div>
				<div>
					<label htmlFor="password">Password : </label>
					<input type="password" name="password" value={formData.password} onChange={handleChange} className="border"/>
				</div>
				<div>
					<label htmlFor="phoneNumber">Phone : </label>
					<input type="text" name="phoneNumber" value={formData.phoneNumber} onChange={handleChange} className="border"/>
				</div>
				<button type="submit" className="border p-1.5 bg-sky-700 text-white">Register</button>
      </form>
    </>
  )
}