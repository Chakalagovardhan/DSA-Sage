import React from 'react'
import { Routes,Router, Route } from 'react-router-dom'
import ExamPortal from '../components/ExamPortal'
const AppRouters = () => {
  return (
    <Routes>
        <Route path='/examportal' element={<ExamPortal />}/>


    </Routes>
  )
}

export default AppRouters
