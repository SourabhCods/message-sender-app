import { useState } from 'react';
import axios from 'axios';
import { Form as AntForm, Input, Button, message, Card } from 'antd';
import './form.css'; // custom styles

const FormComponent = () => {
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: '',
    whatsappNumber: ''
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async () => {
    try {
      const res = await axios.post('http://localhost:3000', formData);
      console.log(res.data);
      message.success('✅ Details sent via WhatsApp!');
    } catch (error) {
      console.error(error);
      message.error('❌ Failed to send details.');
    }
  };

  return (
    <div className="form-container">
      <Card className="form-card" bordered={false}>
        <h2 className="form-title">Send Your Details</h2>
        <AntForm layout="vertical" onFinish={handleSubmit}>
          <AntForm.Item label="First Name">
            <Input
              name="firstName"
              value={formData.firstName}
              onChange={handleChange}
              className="animated-input"
              placeholder="Enter first name"
            />
          </AntForm.Item>

          <AntForm.Item label="Last Name">
            <Input
              name="lastName"
              value={formData.lastName}
              onChange={handleChange}
              className="animated-input"
              placeholder="Enter last name"
            />
          </AntForm.Item>

          <AntForm.Item label="Email">
            <Input
              name="email"
              value={formData.email}
              onChange={handleChange}
              className="animated-input"
              placeholder="Enter email"
            />
          </AntForm.Item>

          <AntForm.Item label="WhatsApp Number">
            <Input
              name="whatsappNumber"
              value={formData.whatsappNumber}
              onChange={handleChange}
              className="animated-input"
              placeholder="Enter WhatsApp number"
            />
          </AntForm.Item>

          <Button type="primary" htmlType="submit" className="submit-button">
            🚀 Send Message
          </Button>
        </AntForm>
      </Card>
    </div>
  );
};

export default FormComponent;
