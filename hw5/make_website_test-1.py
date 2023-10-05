import unittest

from make_website import *

resume = load_in_lines("resume.txt")
resume_projects_w_whitespace = load_in_lines("TestResumes/resume_projects_w_whitespace/resume.txt")
resume_project_with_blanks = load_in_lines("TestResumes/resume_projects_with_blanks/resume.txt")


class MakeWebsite_Test(unittest.TestCase):

    def test_surround_block(self):
        # test text with surrounding h1 tags
        self.assertEqual("<h1>Eagles</h1>", surround_block('h1', 'Eagles'))

        # test text with surrounding h2 tags
        self.assertEqual("<h2>Red Sox</h2>", surround_block('h2', 'Red Sox'))

        # test text with surrounding p tags
        self.assertEqual('<p>Lorem ipsum dolor sit amet, consectetur ' +
                         'adipiscing elit. Sed ac felis sit amet ante porta ' +
                         'hendrerit at at urna.</p>',
                         surround_block('p', 'Lorem ipsum dolor sit amet, consectetur ' +
                                        'adipiscing elit. Sed ac felis sit amet ante porta ' +
                                        'hendrerit at at urna.'))

    def test_create_email_link(self):

        # test email with @ sign
        self.assertEqual(
            '<a href="mailto:lbrandon@wharton.upenn.edu">lbrandon[aT]wharton.upenn.edu</a>',
            create_email_link('lbrandon@wharton.upenn.edu')
        )

        # test email with @ sign
        self.assertEqual(
            '<a href="mailto:krakowsky@outlook.com">krakowsky[aT]outlook.com</a>',
            create_email_link('krakowsky@outlook.com')
        )

        # test email without @ sign
        self.assertEqual(
            '<a href="mailto:lbrandon.at.seas.upenn.edu">lbrandon.at.seas.upenn.edu</a>',
            create_email_link('lbrandon.at.seas.upenn.edu')
        )

    def test_get_name(self):

        # test a valid name 1
        self.assertEqual(
            "I.M. Student", get_name(["I.M. Student\n"])
        )

        # test a valid name, with last name in lower case
        self.assertEqual(
            "I.M. student", get_name(["I.M. student\n"])
        )

        # test an invalid name, with last name in lower case
        self.assertEqual(
            "Invalid Name", get_name(["brandon Krakowsky\n"])
        )
    
    def test_section_indexes(self):

        # test given resume index
        self.assertEqual(
            (1, 3, 5, 6), get_section_indexes(resume)
        )

        self.assertEqual(
            (1, 3, 8, 9), get_section_indexes(resume_projects_w_whitespace)
        )

        self.assertEqual(
            (1, 3, 8, 9), get_section_indexes(resume_project_with_blanks)
        )


    def test_get_email(self):

        # test an invalid email with number in 1st part
        self.assertEqual(
            "", get_email(["lbrandon2@wharton.upenn.edu\n"], 0)
        )

        # test an invalid email with number in 2nd part
        self.assertEqual(
            "", get_email(["lbrandon@wharton2.upenn.edu\n"], 0)
        )

        # test a valid email
        self.assertEqual(
            "lbrandon@wharton.upenn.edu", get_email(["lbrandon@wharton.upenn.edu\n"], 0)
        )

    def test_get_courses(self):

        # test give resume
        self.assertEqual(
            ['Programming Languages and Techniques', 'Biomedical image analysis', 'Software Engineering'],
            get_courses(["Courses :- Programming Languages and Techniques," +
                        "Biomedical image analysis, Software Engineering\n"], 0)
        )

        self.assertEqual(
            ['CIT590', 'AB120'],
            get_courses(["Courses - CIT590, AB120\n"], 0)
        )

        self.assertEqual(
            ['CIT590', 'AB120'],
            get_courses(["Courses --=-==           CIT590       , AB120          \n"], 0)
        )
    
    def test_get_project(self):

        self.assertEqual(
            ['CancerDetector.com, New Jersey, USA - Project manager, codified the assessment and mapped it to the CancerDetector ontology. Member of the UI design team, designed the portfolio builder UI and category search pages UI. Reviewed existing rank order and developed new search rank order approach.', 
             'Biomedical Imaging - Developed a semi-automatic image mosaic program based on SIFT algorithm (using Matlab)'],
            get_project(resume, 3, 5)
        )

        self.assertEqual(
            ['CancerDetector.com, New Jersey, USA - Project manager, codified the assessment and mapped it to the CancerDetector ontology. Member of the UI design team, designed the portfolio builder UI and category search pages UI. Reviewed existing rank order and developed new search rank order approach.', 
             'Biomedical Imaging - Developed a semi-automatic image mosaic program based on SIFT algorithm (using Matlab)'],
            get_project(resume_projects_w_whitespace, 3, 8)
        )

        self.assertEqual(
            ['CancerDetector.com, New Jersey, USA - Project manager, codified the assessment and mapped it to the CancerDetector ontology. Member of the UI design team, designed the portfolio builder UI and category search pages UI. Reviewed existing rank order and developed new search rank order approach.', 
             'Biomedical Imaging - Developed a semi-automatic image mosaic program based on SIFT algorithm (using Matlab)'],
            get_project(resume_project_with_blanks, 3, 8)
        )


if __name__ == '__main__':
    unittest.main()
