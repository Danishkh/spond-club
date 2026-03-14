import { useState } from 'react'
import { Group, FormData } from '../../utils/types.ts'
import StepOne from './StepOne.tsx'
import StepTwo from './StepTwo.tsx'
import StepThree from './StepThree.tsx'

interface WizardProps {
  group: Group
}

export default function Wizard({ group }: WizardProps) {
  const [step, setStep] = useState<number>(1)
  const [formData, setFormData] = useState<FormData>({
    memberTypeId: '',
    firstName: '',
    lastName: '',
    email: '',
    phone: '',
    birthDate: '',
  })

  const next = () => setStep(s => s + 1)
  const back = () => setStep(s => s - 1)
  const stepNumbers = [1, 2, 3]

  const update = (fields: Partial<FormData>) => setFormData(prev => ({ ...prev, ...fields }))

  const resetSteps = () => {
    setStep(1)
    setFormData({
      memberTypeId: '',
      firstName: '',
      lastName: '',
      email: '',
      phone: '',
      birthDate: '',
    })
  }

  return (
    <div className="bg-white rounded-2xl shadow-md w-full max-w-2xl p-8">
      <div className="flex items-center justify-between mb-8">
        {stepNumbers.map(stepNumber => (
          <div key={stepNumber} className="flex items-center">
            <div className={`w-8 h-8 rounded-full flex items-center justify-center text-sm font-semibold
              ${step >= stepNumber ? 'bg-blue-600 text-white' : 'bg-gray-200 text-gray-500'}`}>
              {stepNumber}
            </div>
          </div>
        ))}
      </div>

      {step === 1 && <StepOne group={group} formData={formData} update={update} next={next} />}
      {step === 2 && <StepTwo formData={formData} update={update} next={next} back={back} />}
      {step === 3 && <StepThree group={group} formData={formData} back={back} onReset={resetSteps}/>}
    </div>
  )
}