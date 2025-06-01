#!/bin/python
import os
import enum
from pathlib import Path
from matplotlib import pyplot
import pandas

pandas.set_option('display.max_rows', None)
pandas.set_option('display.max_column', None)

class JobCategory(enum.Enum):
    ADMINISTRATIVE = 1
    TECHNICAL = 2
    MANAGERIAL = 3

class Gender(enum.Enum):
    MALE = 1
    FEMALE = 2

class DataGrouping(enum.Enum):
    UNGROUPED = 0
    GROUPED = 1

class DataType(enum.Enum):
    QUANTITATIVE_DISCRETE = 0
    QUANTITATIVE_CONTINUOUS = 1
    QUALITATIVE_NOMINAL = 2
    QUALITATIVE_ORDINAL = 3

class ChartType(enum.Enum):
    PIE = 0
    BAR = 1
    HISTOGRAM = 2
    BOX_WHISKER = 3

compute_configs = [
    {
        'name': 'Employee',
        'file': Path('dataset', 'employee.xls'),
        'config': {
            'years_of_education': {
                'data_grouping': DataGrouping.GROUPED,
                'data_type': DataType.QUANTITATIVE_DISCRETE,
                'chart_type': [
                    ChartType.HISTOGRAM,
                    ChartType.BOX_WHISKER,
                ],
            },
            'job_category': {
                'data_grouping': DataGrouping.UNGROUPED,
                'data_type': DataType.QUALITATIVE_NOMINAL,
                'chart_type': [
                    ChartType.PIE,
                    ChartType.BAR,
                ],
            },
            'annual_salary': {
                'data_grouping': DataGrouping.GROUPED,
                'data_type': DataType.QUANTITATIVE_CONTINUOUS,
                'chart_type': [
                    ChartType.HISTOGRAM,
                    ChartType.BOX_WHISKER,
                ],
            },
        }
    },
    {
        'name': 'Voter',
        'file': Path('dataset', 'voter.xls'),
        'config': {
            'years_of_education': {
                'data_grouping': DataGrouping.GROUPED,
                'data_type': DataType.QUANTITATIVE_DISCRETE,
                'chart_type': [
                    ChartType.HISTOGRAM,
                    ChartType.BOX_WHISKER,
                ],
            },
            'age': {
                'data_grouping': DataGrouping.GROUPED,
                'data_type': DataType.QUANTITATIVE_DISCRETE,
                'chart_type': [
                    ChartType.HISTOGRAM,
                    ChartType.BOX_WHISKER,
                ],
            },
            'gender': {
                'data_grouping': DataGrouping.UNGROUPED,
                'data_type': DataType.QUALITATIVE_NOMINAL,
                'chart_type': [
                    ChartType.PIE,
                    ChartType.BAR,
                ],
            },
        }
    },
]

def build_frequency_distribution_table(data, column_name):
    frequency = dict(sorted(data[column_name].value_counts().to_dict().items()))
    distribution_table_attributes = {
        column_name: frequency.keys(),
        'frequency': frequency.values(),
    }

    # frequency table dataframe
    dataframe = pandas.DataFrame(distribution_table_attributes)
    dataframe['relative_frequency'] = dataframe['frequency'] / dataframe['frequency'].sum()
    dataframe['percentiles'] = (dataframe['frequency'] / dataframe['frequency'].sum()) * 100
    dataframe['cumulative_frequency'] = dataframe['frequency'].cumsum()
    return dataframe

def get_labels(to_convert, column_name):
    if column_name not in ('job_category', 'gender'):
        return to_convert

    converted = []
    for i in to_convert:
        if column_name == 'job_category':
            converted.append(JobCategory(i).name)
        if column_name == 'gender':
            converted.append(Gender(i).name)

    return converted

def compute_answers():
    for compute_config in compute_configs:
        loaded_data = pandas.read_excel(compute_config.get('file'))

        for column_name in loaded_data.keys():
            config = compute_config.get('config').get(column_name)
            result_path = Path('results', compute_config.get('name'), column_name)
            if not os.path.exists(result_path):
                os.makedirs(result_path)

            # Sub-question 1
            dataframe = build_frequency_distribution_table(loaded_data, column_name)
            for chart_type in config.get('chart_type'):
                if chart_type == ChartType.PIE: 
                    labels = get_labels(dataframe.get(column_name), column_name)
                    data = dataframe.get('frequency')

                    pyplot.figure(figsize=(10, 5))
                    pyplot.title('Percentages of {}'.format(column_name))
                    pyplot.pie(data, labels=labels, autopct='%1.2f%%')
                    pyplot.savefig(Path(result_path, 'pie.png'))
                    pyplot.cla()
                if chart_type == ChartType.BAR:
                    labels = get_labels(dataframe.get(column_name), column_name)
                    data = dataframe.get('frequency')

                    pyplot.figure(figsize=(10, 5))
                    pyplot.bar(labels, data)
                    pyplot.title('Frequency distribution of {}'.format(column_name))
                    pyplot.ylabel('Frequency')
                    pyplot.savefig(Path(result_path, 'bar.png'))
                    pyplot.cla()
                if chart_type == ChartType.HISTOGRAM:
                    pyplot.figure(figsize=(10, 6))
                    n, bins, _ = pyplot.hist(loaded_data[column_name], ec='black')
                    pyplot.title('Frequency distribution of {}'.format(column_name))
                    pyplot.ylabel('Frequency')
                    pyplot.xlabel(column_name)
                    bin_centers = 0.5 * (bins[1:] + bins[:-1])
                    pyplot.plot(bin_centers, n)
                    pyplot.savefig(Path(result_path, 'histogram.png'))
                    pyplot.cla()
                if chart_type == ChartType.BOX_WHISKER:
                    pyplot.figure(figsize=(10, 5))
                    pyplot.boxplot(loaded_data[column_name])
                    pyplot.ylabel(column_name)
                    pyplot.savefig(Path(result_path, 'box_whisker.png'))
                    pyplot.cla()

            text_output = 'Data for "{column_name}" column in {config_name}.\n\n{dataframe}\n\n'.format(column_name=column_name, dataframe=dataframe.to_string(index=False), config_name=compute_config.get('name'))
            if config.get('data_type') not in (DataType.QUALITATIVE_NOMINAL, DataType.QUALITATIVE_ORDINAL):
                # Contains mean, std, and quartiles.
                data_summary = loaded_data[column_name].describe()

                # Sub-question 2
                median = loaded_data[column_name].median()

                # Sub-question 3
                mad = loaded_data[column_name].mad()
                interquartile_range = data_summary.get('75%') - data_summary.get('25%')

                # Sub-question 4
                skewness = (3 * (data_summary.get('mean') - median)) / data_summary.get('std')

                text_output += 'Mean: {mean}\nMedian: {median}\nMode: {mode}\nStandard Deviation: {standard_deviation}\nMean Absolute Deviation: {mad}\nInterquartile Range: {interquartile_range}\nSkewness: {skewness}\n'.format(
                    mean=data_summary.get('mean'),
                    median=median,
                    mode=dataframe['frequency'].max(),
                    standard_deviation=data_summary.get('std'),
                    mad=mad,
                    interquartile_range=interquartile_range,
                    skewness=skewness
                )

            print('='* 50)
            print(text_output)

            with open(Path(result_path, 'summary.txt'), 'w') as file:
                file.write(text_output)

if __name__ == '__main__':
    compute_answers()